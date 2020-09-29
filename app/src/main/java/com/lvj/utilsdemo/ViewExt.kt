package com.lvj.utilsdemo

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View


/*
*
*  这个类是代码给View设置shape 圆角 渐变等属性的拓展函数
*
*/

val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()


inline var View.shape: GradientDrawable
    get() = GradientDrawable()
    set(value) {
        background = value
    }

inline fun shape(init: GradientDrawable.() -> Unit) = GradientDrawable().apply(init)

/**
 * 圆角
 */
inline var GradientDrawable.corner_radius: Int
    get() = -1
    set(value) {
        cornerRadius = value.dp.toFloat()
    }

/**
 * 渐变色
 */
inline var GradientDrawable.gradient_colors: List<String>
    get() = emptyList()
    set(value) {
        colors = value.map { Color.parseColor(it) }.toIntArray()
    }

/**
 * 自定义的描边属性
 */
data class Stroke(
    var width: Int = 0,
    var color: String = "#000000"
)

/**
 * 描边
 */
inline var GradientDrawable.strokeAttr: Stroke?
    get() = null
    set(value) {
        value?.apply {
            setStroke(width.dp, Color.parseColor(color))
        }
    }

