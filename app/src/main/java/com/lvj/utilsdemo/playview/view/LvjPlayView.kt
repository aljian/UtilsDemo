package com.lvj.utilsdemo.playview.view

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.util.AttributeSet
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import com.lvj.utilsdemo.playview.HideType
import com.lvj.utilsdemo.playview.PlayActionListener
import com.lvj.utilsdemo.playview.ScreenMode
import com.lvj.utilsdemo.playview.ViewAction
import com.lvj.utilsdemo.playview.gesture.GestureView
import com.lvj.utilsdemo.playview.utils.NetWatchDog
import com.lvj.utilsdemo.playview.utils.OrientationWatchDog
import com.lvj.utilsdemo.util.logi

class LvjPlayView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    init {
        initView()
    }

    //外部回调
    private var mPlayActionListener: PlayActionListener? = null

    //播放
    private var mSurfaceView: SurfaceView? = null

    //手势控制
    private var mGestureView: GestureView? = null

    //封面
    private var mCoverView: ImageView? = null

    //上下控制器 返回 播放进度的seekbar等
    private var mControlView: ControlView? = null

    //屏幕方向监听
    private var mOrientationWatchDog: OrientationWatchDog? = null

    //网络变化监听
    private var mNetWatchDog: NetWatchDog? = null

    //当前屏幕方向
    private var mCurrentScreenMode: ScreenMode = ScreenMode.Port

    //是否锁定屏幕方向
    private var mIsFullScreenLocked = false

    fun setPlayActionListener(listener: PlayActionListener) {
        this.mPlayActionListener = listener
    }

    private fun initView() {
        //保持屏幕常亮
        keepScreenOn = true
        //播放用的SurfaceView
        initSurfaceView()
        //播放器
        initPlayer()
        //封面
        initCoverView()
        //手势
        initGestureView()
        //控制栏
        initControlView()
        //屏幕方向监听
        initOrientationWatchdog()
        //网络监听
        initNetWatchDog()
        //先隐藏UI 防止加载中用户点击
        hideGestureAndControlViews()
    }

    private fun hideGestureAndControlViews() {
//        mControlView?.hide(HideType.Normal)
//        mGestureView?.hide(HideType.Normal)
    }

    /**
     * 播放用的SurfaceView
     */
    private fun initSurfaceView() {
        mSurfaceView = SurfaceView(context.applicationContext)
        mSurfaceView?.let {
            addSubView(it)
            val holder = it.holder
            holder.addCallback(object : SurfaceHolder.Callback {

                override fun surfaceCreated(holder: SurfaceHolder?) {
                    logi("surfaceCreated")
                }

                override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
                    logi("surfaceChanged")
                }

                override fun surfaceDestroyed(holder: SurfaceHolder?) {
                    logi("surfaceDestroyed")
                }

            })
        }
    }

    /**
     * 播放器
     */
    private fun initPlayer() {

    }

    private var mCurrentPosition = 0
    private var isFirstTouch = true

    /**
     * 手势
     */
    private fun initGestureView() {
        mGestureView = GestureView(context)
        addSubView(mGestureView!!)
        mGestureView?.setOnGestureListener(object : GestureView.GestureListener {

            override fun onHorizontalDistance(downX: Float, nowX: Float) {
                //水平滑动
                if (isFirstTouch) {
                    mCurrentPosition = mControlView!!.getVideoPosition()
                    isFirstTouch = false
                }
                val fl = (nowX - downX) / width
                var targetPosition = mCurrentPosition + (fl * 100).toInt()
                if (targetPosition < 0) targetPosition = 0
                if (targetPosition > 100) targetPosition = 100
                mControlView!!.setVideoPosition(targetPosition)
            }

            override fun onLeftVerticalDistance(downY: Float, nowY: Float) {
                //左侧垂直滑动
            }

            override fun onRightVerticalDistance(downY: Float, nowY: Float) {
                //右侧垂直滑动
            }

            override fun onGestureEnd() {
                //滑动结束
                isFirstTouch = true
            }

            override fun onSingleTap() {
                //单击 显示或隐藏控制栏
                if (mControlView != null) {
                    if (mControlView!!.visibility != VISIBLE) {
                        mControlView!!.show()
                    } else {
                        mControlView!!.hide(HideType.Normal)
                    }
                }
            }

            override fun onDoubleTap() {
                //双击
            }

        })
    }

    /**
     * 封面
     */
    private fun initCoverView() {
        mCoverView = ImageView(context)
        mCoverView?.run {
            setImageResource(android.R.color.holo_blue_light)
            scaleType = ImageView.ScaleType.FIT_XY
            addSubView(this)
        }
    }

    /**
     * 控制栏
     */
    private fun initControlView() {
        mControlView = ControlView(context)
        addSubView(mControlView!!)
        mControlView?.setOnControlViewListener(object : ControlView.ControlViewListener {

            override fun onBackClickListener() {
                logi("back")
                mPlayActionListener?.onBackClick()
                if (mCurrentScreenMode === ScreenMode.Full) {
                    //设置为小屏状态
                    changeScreenMode(ScreenMode.Port, false)
                } else {
                    val mContext = context
                    if (mContext is Activity) {
                        mContext.finish()
                    }
                }
            }

            override fun onMenuClickListener() {
                logi("menu")
            }

            override fun onSeekStart(position: Int) {
                logi("start = $position")
            }

            override fun onSeekEnd(position: Int) {
                logi("end = $position")
            }

            override fun onProgressChanged(position: Int) {
                logi("changed = $position")
            }

            override fun onScreenModeChanged() {
                logi("onScreenModeChanged")
                val targetMode = if (mCurrentScreenMode == ScreenMode.Full) ScreenMode.Port else ScreenMode.Full
                changeScreenMode(targetMode, false)
            }

            override fun onScreenLockClick() {
                lockScreen(!mIsFullScreenLocked)
            }

            override fun onPlayStateClick() {
                changePlayState()
            }

        })
    }

    /**
     * 播放状态改变
     */
    private fun changePlayState() {


    }


    /**
     *锁定屏幕 锁定时 只显示锁按钮且手势不可用
     */
    private fun lockScreen(lockScreen: Boolean) {
        mIsFullScreenLocked = lockScreen
        mControlView?.setScreenLockStatus(mIsFullScreenLocked)
        mGestureView?.setScreenLockStatus(mIsFullScreenLocked)
    }

    private fun addSubView(view: View) {
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        params.gravity = Gravity.CENTER
        addView(view, params)
    }

    fun onResume() {
        mOrientationWatchDog?.startWatch()
        mNetWatchDog?.startWatch()
    }

    fun onStop() {
        mOrientationWatchDog?.stopWatch()
        mNetWatchDog?.stopWatch()
    }

    fun onDestroy() {
        removeAllViews()
        mSurfaceView = null
        mGestureView = null
        mControlView?.destroy()
        mControlView = null
        mCoverView = null
        mOrientationWatchDog?.stopWatch()
        mOrientationWatchDog = null
    }


    /**
     * 屏幕方向监听
     */
    private fun initOrientationWatchdog() {
        mOrientationWatchDog = OrientationWatchDog(context.applicationContext)
        mOrientationWatchDog!!.setOnOrientationListener(object : OrientationWatchDog.OnOrientationListener {

            override fun changedToLandForwardScape(fromPort: Boolean) {
                //如果不是从竖屏变过来，也就是一直是横屏的时候，就不用操作了
                if (!fromPort) return
                changeScreenMode(ScreenMode.Full, false)
                mPlayActionListener?.orientationChange(fromPort, mCurrentScreenMode)
            }

            override fun changedToLandReverseScape(fromPort: Boolean) {
                //如果不是从竖屏变过来，也就是一直是横屏的时候，就不用操作了
                if (!fromPort) return
                changeScreenMode(ScreenMode.Full, true)
                mPlayActionListener?.orientationChange(fromPort, mCurrentScreenMode)
            }

            override fun changedToPortrait(fromLand: Boolean) {
                //屏幕转为竖屏
                if (mIsFullScreenLocked) return
                if (mCurrentScreenMode == ScreenMode.Full) {
                    if (fromLand) {
                        changeScreenMode(ScreenMode.Port, false)
                    }
                }
                mPlayActionListener?.orientationChange(fromLand, mCurrentScreenMode)
            }

        })
    }

    /**
     * 网络监听
     */
    private fun initNetWatchDog() {
        mNetWatchDog = NetWatchDog(context.applicationContext)
        mNetWatchDog!!.setOnNetChangeListener(object : NetWatchDog.NetChangeListener {

            override fun onChangeTo4G() {
                logi("onChangeTo4G")
                changeTo4G()
            }

            override fun onChangeToWifi() {
                logi("onChangeToWifi")
                changeToWifi()
            }

            override fun onNetDisconnected() {
                logi("onNetDisconnected")
                //因为网络切换的时候有时候会先回调这个所以不准
            }

        })
        mNetWatchDog!!.setNetConnectedListener(object : NetWatchDog.NetConnectedListener {

            override fun onReNetConnected(isReconnect: Boolean) {
                logi("onReNetConnected")
            }

            override fun onNetUnConnected() {
                logi("onNetUnConnected")
            }
        })
    }


    private fun changeScreenMode(targetMode: ScreenMode, isReverse: Boolean) {

        var finalScreenMode = targetMode

        if (mIsFullScreenLocked) {
            finalScreenMode = ScreenMode.Full
        }

        if (targetMode != mCurrentScreenMode) {
            mCurrentScreenMode = finalScreenMode
        }

        mControlView?.setScreenModeStatus(finalScreenMode)

        val mContext = context
        if (mContext is Activity) {
            if (finalScreenMode == ScreenMode.Full) {
                if (isReverse) {
                    mContext.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
                } else {
                    mContext.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                }
            } else if (finalScreenMode == ScreenMode.Port) {
                mContext.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
    }

    /**
     * 流量
     */
    private fun changeTo4G() {

    }

    /**
     * wifi
     */
    private fun changeToWifi() {

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && mCurrentScreenMode == ScreenMode.Full) {
            return if (mIsFullScreenLocked) {
                false
            } else {
                changeScreenMode(ScreenMode.Port, false)
                mPlayActionListener?.orientationChange(true, mCurrentScreenMode)
                false
            }
        }
        return true
    }
}