package com.lvj.utilsdemo.playview.gesture

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.lvj.utilsdemo.util.isInLeftScreen
import com.lvj.utilsdemo.util.isInRightScreen
import kotlin.math.abs

/**
 * 手势控制类
 */
class GestureControl(val mContext: Context, mGesturebleView: View) {

    //是否水平
    private var isInHorizenalGesture = false

    //是否右边垂直
    private var isInRightGesture = false

    //是否左边垂直
    private var isInLeftGesture = false

    //是否允许手势滑动
    private var isGestureEnable = true

    //手势决定器
    private val mGestureDetector: GestureDetector

    //滑动监听
    private var mGestureListener: GestureView.GestureListener? = null


    private val mOnGestureListener = object : GestureDetector.SimpleOnGestureListener() {

        private var mXDown = 0f

        override fun onDown(e: MotionEvent?): Boolean {
            e?.let {
                this.mXDown = it.x
            }
            return true
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            mGestureListener?.onSingleTap()
            return false
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            mGestureListener?.onDoubleTap()
            return false
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            //如果关闭了手势 则不处理
            if (!isGestureEnable || e1 == null || e2 == null) return false

            if (abs(distanceX) > abs(distanceY)) {
                //水平滑动
                if (isInLeftGesture || isInRightGesture) {
                    //如果之前是垂直滑动则不管 防止用户手抖,滑动拐弯{手动狗头}
                } else {
                    isInHorizenalGesture = true
                }

            } else {
                //垂直滑动
            }
            if (isInHorizenalGesture) {
                mGestureListener?.onHorizontalDistance(e1.x, e2.x)
            } else {
                if (mContext.isInLeftScreen(mXDown.toInt())) {
                    isInLeftGesture = true
                    mGestureListener?.onLeftVerticalDistance(e1.y, e2.y)
                } else if (mContext.isInRightScreen(mXDown.toInt())) {
                    isInRightGesture = true
                    mGestureListener?.onRightVerticalDistance(e1.y, e2.y)
                }
            }

            return true
        }

    }

    init {
        mGestureDetector = GestureDetector(mContext, mOnGestureListener)
        mGesturebleView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    mGestureListener?.onGestureEnd()
                    isInLeftGesture = false
                    isInRightGesture = false
                    isInHorizenalGesture = false
                }
            }
            return@setOnTouchListener mGestureDetector.onTouchEvent(event)
        }
    }


    fun setOnGestureControlListener(mGestureListener: GestureView.GestureListener) {
        this.mGestureListener = mGestureListener
    }

    /**
     * 是否允许手势控制
     */
    fun enableGesture(enable: Boolean) {
        this.isGestureEnable = enable
    }
}