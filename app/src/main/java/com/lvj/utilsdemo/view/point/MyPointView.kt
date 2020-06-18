package com.lvj.utilsdemo.view.point

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.BounceInterpolator


data class Point(var radius: Float)

class MyPointView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var mCurPoint: Point? = null

    private var mWidth = 0f
    private var mHeight = 0f

    private val mPaint = Paint()

    init {
        mPaint.isAntiAlias = true
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.FILL
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mWidth = w.toFloat()
        mHeight = h.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mCurPoint?.run {
            canvas.drawCircle(mWidth / 2, mHeight / 2, radius, mPaint)
        }
    }

    fun doPointAnim() {
        val animator = ValueAnimator.ofObject(PointEvaluator(), Point(20f), Point(200f))
        animator.addUpdateListener {
            mCurPoint = it.animatedValue as Point
            invalidate()
        }
        animator.duration = 3000
        animator.interpolator = BounceInterpolator()
        animator.start()
    }


}