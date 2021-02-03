package com.lvj.utilsdemo.rv.path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CanvasView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private lateinit var mCanvas: Canvas
    private lateinit var mBitmap: Bitmap

    private var mPath: Path = Path()
    private val mPaint: Paint = Paint()

    init {
        mPaint.run {
            isAntiAlias = true
            color = Color.BLUE
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE
            strokeWidth = 10f
        }
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(mBitmap, 0f, 0f, null)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled) return false
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mPath.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                mPath.lineTo(event.x, event.y)
            }
        }
        mCanvas.drawPath(mPath, mPaint)
        invalidate()
        return true
    }

    fun getPath() = mPath

    fun setPath(path: Path) {
        this.mPath = path
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitmap)
        mCanvas.drawPath(mPath, mPaint)
        invalidate()
    }

    fun clear() {
        setPath(Path())
    }
}