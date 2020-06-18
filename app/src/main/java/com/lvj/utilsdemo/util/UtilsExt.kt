package com.lvj.utilsdemo.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson

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


