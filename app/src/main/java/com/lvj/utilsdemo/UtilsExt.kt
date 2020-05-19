package com.lvj.utilsdemo

import android.content.Context
import android.widget.Toast

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

