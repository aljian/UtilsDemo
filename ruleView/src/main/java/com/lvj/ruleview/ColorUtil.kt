package com.lvj.ruleview

import android.graphics.Color
import androidx.annotation.ColorInt

object ColorUtil {

    @ColorInt
    fun getColor(startColor: Int, endColor: Int, radio: Float): Int {
        val redStart = Color.red(startColor)
        val blueStart = Color.blue(startColor)
        val greenStart = Color.green(startColor)

        val redEnd = Color.red(endColor)
        val blueEnd = Color.blue(endColor)
        val greenEnd = Color.green(endColor)

        val red = (redStart + (redEnd - redStart) * radio + 0.5).toInt()
        val blue = (blueStart + (blueEnd - blueStart) * radio + 0.5).toInt()
        val green = (greenStart + (greenEnd - greenStart) * radio + 0.5).toInt()
        return Color.argb(255, red, green, blue)
    }


}