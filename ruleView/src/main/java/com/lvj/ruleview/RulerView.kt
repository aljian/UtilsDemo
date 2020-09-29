package com.lvj.ruleview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.widget.Scroller
import androidx.annotation.ColorInt
import kotlin.math.abs

class RulerView @JvmOverloads constructor(mContext: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(mContext, attrs, defStyleAttr) {

    /**
     * 渐变色起始色
     */
    @ColorInt
    private val startColor = Color.parseColor("#ff3415b0")

    /**
     * 渐变色结束色
     */
    @ColorInt
    private val endColor = Color.parseColor("#ffcd0074")

    /**
     * 指示器颜色
     */
    @ColorInt
    var indicatorColor = startColor

    //控件宽高
    private var mWidth = 0
    private var mHeight = 0

    /**
     * 线条宽度
     */
    private val lineWidth = 12

    //长中短 刻线高度
    private var maxLineHeight = 0
    private var midLineHeight = 0
    private var minLineHeight = 0

    /**
     * 指示器半径
     */
    private var indicatorRadius = lineWidth / 2

    //指示器开始与结束数字
    private var startNum = 0
    private var endNum = 40

    /***
     * 每个刻度代表的数字单位
     */
    private var stepNum = 1

    /**
     * 刻度间距
     */
    private val lineSpacing = 2 * lineWidth

    /**
     * 第一刻度距离当前的偏移量 一定小于0
     */
    private var offsetStart = 0f


    //辅助计算 主要用于惯性计算
    private var scroller: Scroller

    //画笔
    private val paint: Paint
    private val textPaint: Paint

    //刻度文字大小
    private val textSize = 96
    private val textHeight: Float

    //跟踪用户手指滑动速度
    private val velocityTracker: VelocityTracker

    //惯性滑动的最小速度
    private val minVelocityX: Int

    //当前手指移动的距离
    private var movedX = 0f

    //手指按下 初始滑动的X
    private var downX = 0f

    private val countNum: Int

    init {
        paint = Paint()
        paint.color = startColor
        paint.style = Paint.Style.FILL

        textPaint = Paint()
        textPaint.color = startColor
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = textSize.toFloat()
        textPaint.typeface = Typeface.DEFAULT_BOLD
        val fontMetrics = textPaint.fontMetrics
        textHeight = fontMetrics.descent - fontMetrics.ascent

        scroller = Scroller(context)

        velocityTracker = VelocityTracker.obtain()
        minVelocityX = ViewConfiguration.get(context).scaledMinimumFlingVelocity

        countNum = (endNum - startNum) / stepNum
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        //最长的刻线为 三分之二高
        maxLineHeight = mHeight * 2 / 3
        midLineHeight = maxLineHeight * 4 / 5
        minLineHeight = maxLineHeight * 3 / 5

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制刻度
        for (i in 0..countNum) {
            var lineHeight = minLineHeight
            if (i % 10 == 0) {
                lineHeight = maxLineHeight
            } else if (i % 5 == 0) {
                lineHeight = midLineHeight
            }
            val curColor = ColorUtil.getColor(startColor, endColor, i / (countNum.toFloat()))

            //绘制图形
            val lineLeft = offsetStart + movedX + mWidth / 2 - lineWidth / 2 + i * lineSpacing
            val lineRight = lineLeft + lineWidth
            val rectF = RectF(lineLeft, 4f * indicatorRadius, lineRight, lineHeight.toFloat())
            paint.color = curColor
            canvas.drawRoundRect(rectF, lineWidth / 2f, lineWidth / 2f, paint)

            //绘制文字
            if (i % 10 == 0) {
                textPaint.color = curColor
                canvas.drawText(
                    i.toString(),
                    lineLeft + lineWidth / 2 - textPaint.measureText(i.toString()) / 2,
                    lineHeight + 20 + textHeight,
                    textPaint
                )
            }
        }
        //draw indicator
        val indicatorX = mWidth / 2
        val indicatorY = indicatorRadius
        indicatorColor = ColorUtil.getColor(startColor, endColor, abs((offsetStart + movedX) / (lineSpacing * countNum)))
        paint.color = indicatorColor
        canvas.drawCircle(indicatorX.toFloat(), indicatorY.toFloat(), indicatorRadius.toFloat(), paint)
    }


    fun getSelectedNum(): Int {
        return abs((offsetStart + movedX) / lineSpacing).toInt()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        velocityTracker.addMovement(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                scroller.forceFinished(true)
                downX = event.x
                movedX = 0f
            }
            MotionEvent.ACTION_MOVE -> {
                movedX = event.x - downX
                //边界控制
                if (offsetStart + movedX > 0) {
                    movedX = 0f
                    offsetStart = 0f
                } else if (offsetStart + movedX < -countNum * lineSpacing) {
                    offsetStart = -countNum / lineSpacing.toFloat()
                    movedX = 0f
                }
                listener?.onNumSelect(getSelectedNum())
                postInvalidate()
            }
            MotionEvent.ACTION_UP -> {
                if (offsetStart + movedX <= 0 && (offsetStart + movedX) >= -countNum * lineSpacing) {
                    //手指松开时需要磁吸效果
                    offsetStart = offsetStart + movedX
                    movedX = 0f
                    offsetStart = (offsetStart / lineSpacing).toInt() * lineSpacing.toFloat()
                } else if (offsetStart + movedX > 0) {
                    movedX = 0f
                    offsetStart = 0f
                } else {
                    offsetStart = -countNum * lineSpacing.toFloat()
                    movedX = 0f
                }
                listener?.onNumSelect(getSelectedNum())
                //计算手指放开后的惯性滑动
                velocityTracker.computeCurrentVelocity(500)
                val velocityX = velocityTracker.xVelocity
                if (abs(velocityX) > minVelocityX) {
                    scroller.fling(0, 0, velocityX.toInt(), 0, Int.MIN_VALUE, Int.MAX_VALUE, 0, 0)
                }
                postInvalidate()
            }
        }
        return true
    }

    override fun computeScroll() {
        super.computeScroll()
        if (scroller.computeScrollOffset()) {
            if (scroller.currX == scroller.finalX) {
                if (offsetStart + movedX <= 0 && (offsetStart + movedX) >= -countNum * lineSpacing) {
                    offsetStart = offsetStart + movedX
                    movedX = 0f
                    offsetStart = (offsetStart / lineSpacing).toInt() * lineSpacing.toFloat()
                } else if (offsetStart + movedX > 0) {
                    movedX = 0f
                    offsetStart = 0f
                } else {
                    offsetStart = -countNum * lineSpacing.toFloat()
                    movedX = 0f
                }
            } else {
                //继续惯性滑动
                movedX = (scroller.currX - scroller.startX).toFloat()
                //滑动结束 边界控制
                if (offsetStart + movedX > 0) {
                    movedX = 0f
                    offsetStart = 0f
                    scroller.forceFinished(true)
                } else if ((offsetStart + movedX) < -countNum * lineSpacing) {
                    offsetStart = -countNum * lineSpacing.toFloat()
                    movedX = 0f
                    scroller.forceFinished(true)
                }
            }
        } else {
            if (offsetStart + movedX >= 0) {
                offsetStart = 0f
                movedX = 0f
            }
        }
        listener?.onNumSelect(getSelectedNum())
        postInvalidate()
    }


    private var listener: OnNumSelectListener? = null

    fun setOnNumSelectListener(listener: OnNumSelectListener) {
        this.listener = listener
    }

    interface OnNumSelectListener {
        fun onNumSelect(selectedNum: Int)
    }
}