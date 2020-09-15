package com.lvj.utilsdemo.playview.view

import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.playview.HideType
import com.lvj.utilsdemo.playview.PlayState
import com.lvj.utilsdemo.playview.ScreenMode
import com.lvj.utilsdemo.playview.ViewAction
import kotlinx.android.synthetic.main.view_control.view.*

class ControlView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr), ViewAction {

    private var listener: ControlViewListener? = null
    private var mVideoPosition: Int = 0
    private var mScreenMode = ScreenMode.Port

    //屏幕方向是否锁定
    private var mScreenLocked = false

    //seekBar拖动中
    private var isSeekbarTouching = false

    private var mPlayState = PlayState.NotPlaying
    private var mHideType: HideType? = null

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.view_control, this, true)
        setViewListener()
        updateAllViews()
    }

    private fun setViewListener() {

        play_iv_back.setOnClickListener {
            listener?.onBackClickListener()
        }
        play_iv_menu.setOnClickListener {
            listener?.onMenuClickListener()
        }
        play_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    play_tv_time_current.text = formatProgress(progress)
                    listener?.onProgressChanged(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                isSeekbarTouching = true
                mHideHandler.removeMessages(WHAT_HIDE)
                listener?.onSeekStart(seekBar.progress)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                listener?.onSeekEnd(seekBar.progress)
                isSeekbarTouching = false
                mHideHandler.removeMessages(WHAT_HIDE);
                mHideHandler.sendEmptyMessageDelayed(WHAT_HIDE, DELAY_TIME)
            }

        })

        play_iv_fullscreen.setOnClickListener {
            listener?.onScreenModeChanged()
        }

        play_iv_lock.setOnClickListener {
            listener?.onScreenLockClick()
        }

        play_iv_play_pause.setOnClickListener {
            listener?.onPlayStateClick()
        }
    }

    private fun updateAllViews() {
        updateTitleText()
        updateProgressText()
        updateScreenLock()
        updateFullScreenImg()
        updateTopControlBar()
        updateBottomControlBar()
        updatePlayStateView()
    }

    private fun updatePlayStateView() {
        if (mPlayState == PlayState.Playing) {
            play_iv_play_pause.setImageResource(R.drawable.ic_pause_24)
        } else {
            play_iv_play_pause.setImageResource(R.drawable.ic_play_24)
        }
    }

    //标题文字等设置好不随着播放变动的
    private fun updateTitleText() {
        play_tv_title.text = "这是标题"
    }

    /**
     * 播放过程中变动的
     */
    private fun updateProgressText() {
        play_tv_time_current.text = (mVideoPosition.toString())
        play_tv_time_total.text = "100"
    }

    private fun updateScreenLock() {
        if (mScreenLocked) {
            play_iv_lock.setImageResource(R.drawable.ic_lock)
        } else {
            play_iv_lock.setImageResource(R.drawable.ic_unlock)
        }

        if (mScreenMode == ScreenMode.Port) {
            play_iv_lock.visibility = View.GONE
        } else {
            play_iv_lock.visibility = View.VISIBLE
        }
    }

    private fun updateFullScreenImg() {
        if (mScreenMode == ScreenMode.Port) {
            play_iv_fullscreen.setImageResource(R.drawable.ic_fullscreen_24)
        } else {
            play_iv_fullscreen.setImageResource(R.drawable.ic_fullscreen_exit_24)
        }
    }


    private fun updateBottomControlBar() {
        val canshow = !mScreenLocked
        play_ll_bottom.visibility = if (canshow) VISIBLE else GONE
    }

    private fun updateTopControlBar() {
        val canshow = !mScreenLocked
        play_ll_top.visibility = if (canshow) VISIBLE else GONE
    }


    //屏幕方向变化时要更改的
    private fun updateScreenModeStatus() {
        updateScreenLock()
        updateFullScreenImg()
    }


    interface ControlViewListener {
        fun onBackClickListener()
        fun onMenuClickListener()
        fun onSeekStart(position: Int)
        fun onSeekEnd(position: Int)
        fun onProgressChanged(position: Int)
        fun onScreenModeChanged()
        fun onScreenLockClick()
        fun onPlayStateClick()
    }

    fun setOnControlViewListener(listener: ControlViewListener) {
        this.listener = listener
    }

    fun setVideoPosition(position: Int) {
        this.mVideoPosition = position
        play_seekbar.max = 100
        play_seekbar.progress = position
        play_tv_time_current.text = (mVideoPosition.toString())
        play_tv_time_total.text = "100"
    }

    private fun formatProgress(position: Int): String {
        return position.toString()
    }

    fun getVideoPosition(): Int = mVideoPosition


    override fun reset() {
        mHideType = null
        mVideoPosition = 0
        mPlayState = PlayState.NotPlaying
        isSeekbarTouching = false
        updateAllViews()
    }

    override fun show() {
        visibility = if (mHideType == HideType.End) {
            //错误引起的 不显示
            GONE
        } else {
            updateAllViews()
            VISIBLE
        }
    }

    override fun hide(hideType: HideType) {
        if (mHideType != HideType.End) {
            mHideType = hideType
        }
        visibility = GONE

    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == VISIBLE) {
            //启动5秒隐藏
            hideDelayed()
        }
    }

    companion object {
        const val WHAT_HIDE = 0

        /**
         * 隐藏时间
         */
        const val DELAY_TIME = 5 * 1000L
    }

    private val mHideHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (!isSeekbarTouching) {
                hide(HideType.Normal)
            }
            super.handleMessage(msg)
        }
    }

    private fun hideDelayed() {
        mHideHandler.removeMessages(WHAT_HIDE)
        mHideHandler.sendEmptyMessageDelayed(WHAT_HIDE, DELAY_TIME)
    }

    fun destroy() {
        mHideHandler.removeCallbacksAndMessages(null)
    }

    override fun setScreenModeStatus(mode: ScreenMode) {
        mScreenMode = mode
        updateScreenModeStatus()
    }


    fun setScreenLockStatus(mScreenLock: Boolean) {
        this.mScreenLocked = mScreenLock
        updateScreenLock()
        updateTopControlBar()
        updateBottomControlBar()
    }

    fun setPlayState(state: PlayState) {
        this.mPlayState = state
        updatePlayStateView()
    }
}