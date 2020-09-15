package com.lvj.utilsdemo.util

import android.app.ActivityManager
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
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

fun Context.dp2px(value: Int): Float {
    val density = resources.displayMetrics.density
    return (value * density + 0.5f)
}

fun Context.dp2px(value: Float): Float {
    val density = resources.displayMetrics.density
    return (value * density + 0.5f)
}

fun Context.getScreenWidthPx() = resources.displayMetrics.widthPixels
fun Context.getScreenHeightPx() = resources.displayMetrics.heightPixels

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


