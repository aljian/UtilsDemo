package com.lvj.utilsdemo.textspan

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan


class CustomReplaceSpan : ReplacementSpan() {

    private var mSize = 0

    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        mSize = paint.measureText(text, start, end).toInt()
        return mSize
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence?,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val color = paint.color //保存文字颜色
        paint.color = Color.BLUE //设置背景颜色
        paint.isAntiAlias = true // 设置画笔的锯齿效果

        //设置文字背景矩形，
        // x为span其实左上角相对整个TextView的x值，
        // y为span左上角相对整个View的y值。
        // paint.ascent()获得文字上边缘，
        // paint.descent()获得文字下边缘
        val fl = y + paint.descent()
        val oval = RectF(x, fl, x + mSize, fl + 20f)

        //绘制圆角矩形，第二个参数是x半径，第三个参数是y半径
        canvas.drawRoundRect(oval, 10f, 10f, paint)

        paint.color = color //恢复画笔的文字颜色

        canvas.drawText(text!!, start, end, x, y.toFloat(), paint) //绘制文字

    }

}