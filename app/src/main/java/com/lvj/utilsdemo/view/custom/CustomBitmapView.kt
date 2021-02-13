package com.lvj.utilsdemo.view.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import com.lvj.utilsdemo.R

class CustomBitmapView : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var mWidth = 0
    private var mHeight = 0
    private var mBitmap: Bitmap
    private val mPaint = Paint()

    private val mContent = "Talk is cheep, show me your code "

    init {
        mBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.car)
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 2f
        mPaint.textSize = 25f
        mPaint.color = Color.RED
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(mBitmap, 0f, 0f, mPaint)

        //canvas.drawBitmap(mBitmap, Rect(0, 0, 50, 50), Rect(200, 200, 400, 400), mPaint)

//        val matrix = Matrix()
//        matrix.postTranslate(200f, 200f)
//        canvas.drawBitmap(mBitmap, matrix, mPaint)

//        val baselineX = 0f
//        val baselineY = 100f
//        canvas.drawLine(0f, baselineY, 1000f, baselineY, mPaint)
//        mPaint.color = Color.BLUE
//        canvas.drawText(mContent, baselineX, baselineY, mPaint)

        val path = Path()
        mPaint.color = Color.RED
        path.moveTo(20f,300f)
        path.lineTo(500f,300f)
//        path.addCircle(300f, 300f, 100f, Path.Direction.CCW)
        canvas.drawPath(path, mPaint)

        mPaint.color = Color.BLUE
        mPaint.textAlign = Paint.Align.CENTER
        canvas.drawTextOnPath(mContent, path, 0f, 30f, mPaint)

    }
}