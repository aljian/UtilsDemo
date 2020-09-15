package com.lvj.utilsdemo.playview.gesture

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.lvj.utilsdemo.playview.HideType
import com.lvj.utilsdemo.playview.ScreenMode
import com.lvj.utilsdemo.playview.ViewAction

class GestureView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr), ViewAction {

    interface GestureListener {
        /**
         * 水平滑动距离
         *
         * @param downX 按下位置
         * @param nowX  当前位置
         */
        fun onHorizontalDistance(downX: Float, nowX: Float)

        /**
         * 左边垂直滑动距离
         *
         * @param downY 按下位置
         * @param nowY  当前位置
         */
        fun onLeftVerticalDistance(downY: Float, nowY: Float)

        /**
         * 右边垂直滑动距离
         *
         * @param downY 按下位置
         * @param nowY  当前位置
         */
        fun onRightVerticalDistance(downY: Float, nowY: Float)

        /**
         * 手势结束
         */
        fun onGestureEnd()

        /**
         * 单击事件
         */
        fun onSingleTap()

        /**
         * 双击事件
         */
        fun onDoubleTap()
    }

    private val mGestureControl: GestureControl

    //提供给外部的回调
    private var mOutGestureListener: GestureListener? = null

    //横屏是否锁定屏幕
    private var mIsFullScreenLocked = false

    init {
        mGestureControl = GestureControl(getContext(), this)
        mGestureControl.setOnGestureControlListener(object : GestureListener {
            override fun onHorizontalDistance(downX: Float, nowX: Float) {
                if (mIsFullScreenLocked) return
                mOutGestureListener?.onHorizontalDistance(downX, nowX)
            }

            override fun onLeftVerticalDistance(downY: Float, nowY: Float) {
                if (mIsFullScreenLocked) return
                mOutGestureListener?.onLeftVerticalDistance(downY, nowY)
            }

            override fun onRightVerticalDistance(downY: Float, nowY: Float) {
                if (mIsFullScreenLocked) return
                mOutGestureListener?.onRightVerticalDistance(downY, nowY)
            }

            override fun onGestureEnd() {
                if (mIsFullScreenLocked) return
                mOutGestureListener?.onGestureEnd()
            }

            override fun onSingleTap() {
                //锁屏时单击 要显示锁按钮
                mOutGestureListener?.onSingleTap()
            }

            override fun onDoubleTap() {
                if (mIsFullScreenLocked) return
                mOutGestureListener?.onDoubleTap()
            }

        })
    }

    /**
     *锁定全屏
     * @param locked true 锁定
     */
    fun setScreenLockStatus(locked: Boolean) {
        mIsFullScreenLocked = locked
    }

    fun setOnGestureListener(gestureListener: GestureListener) {
        this.mOutGestureListener = gestureListener
    }

    override fun reset() {

    }

    override fun show() {

    }

    override fun hide(hideType: HideType) {

    }

    override fun setScreenModeStatus(mode: ScreenMode) {

    }
}