package com.lvj.utilsdemo.util

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.io.File

var toast: Toast? = null

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    if (toast == null) {
        toast = Toast.makeText(this, msg, duration)
    }
    toast?.run {
        setText(msg)
        setDuration(duration)
        show()
    }
}

fun Any.toGsonString(): String = Gson().toJson(this)

fun logi(msg: String, tag: String = "tagtag") {
    Log.i(tag, msg)
}

fun loge(msg: String, tag: String = "tagtag") {
    Log.e(tag, msg)
}

fun Context.dp2px(value: Int): Int {
    val density = resources.displayMetrics.density
    return (value * density + 0.5f).toInt()
}

fun Context.dp2px(value: Float): Float {
    val density = resources.displayMetrics.density
    return (value * density + 0.5f)
}

fun Context.dpTopx(value: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
}

fun Context.createRectDrawable(): Drawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE// 矩形
        cornerRadius = 10f// 圆角
        colors = intArrayOf(Color.parseColor("#ff00ff"), Color.parseColor("#800000ff"))//渐变色
        gradientType = GradientDrawable.LINEAR_GRADIENT // 渐变类型
        orientation = GradientDrawable.Orientation.LEFT_RIGHT // 渐变方向
        setStroke(dp2px(2), Color.parseColor("#ffff00")) // 描边宽度和颜色
    }
}


fun Context.getScreenWidthPx() = resources.displayMetrics.widthPixels
fun Context.getScreenHeightPx() = resources.displayMetrics.heightPixels

fun Activity.getHeightPx(): Int {
    val p = Point()
    windowManager.defaultDisplay.getSize(p)
    return p.y
}


fun Context.isPortrait(): Boolean {
    val mOrientation: Int = applicationContext.resources.configuration.orientation
    return mOrientation == Configuration.ORIENTATION_PORTRAIT
}

fun Context.isInLeftScreen(xPos: Int): Boolean = xPos < (this.getScreenWidthPx() / 2)

fun Context.isInRightScreen(xPos: Int): Boolean = xPos > (this.getScreenWidthPx() / 2)


fun Context.getFile() {
    val fileStreamPath = getFileStreamPath("course")
    logi("fileStreamPath =${fileStreamPath.absolutePath}")
    val file = File(filesDir, "course")
    logi("file =${file.absolutePath}")
}


/**
 * 判断app运行状态
 *
 * @return 1: 前台 2:后台  3:不存在即APP未启动
 */
fun Context.isAppAlive(packageName: String = this.packageName): Int {
    try {
        val am = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val list = am.getRunningTasks(20)
        //判断程序是否在栈顶
        return if (list[0].topActivity!!.packageName == packageName) {
            1
        } else {
            //判断程序是否在栈里
            for (info in list) {
                if (info.topActivity!!.packageName == packageName) {
                    return 2
                }
            }
            3 //栈里找不到，返回3
        }
    } catch (e: Exception) {
        return 3
    }
}

fun isTablet(): Boolean {
    return ((Resources.getSystem().configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK)
            >= Configuration.SCREENLAYOUT_SIZE_LARGE)
}


fun View.showSnackbar(text: String, actionTest: String? = null, duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, text, duration)
    if (actionTest != null && block != null) {
        snackbar.setAction(actionTest) {
            block()
        }
    }
    snackbar.show()
}

