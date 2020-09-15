package com.lvj.utilsdemo.retrofit.ext

import com.lvj.utilsdemo.MyApp
import com.lvj.utilsdemo.R
import java.net.ConnectException
import java.net.UnknownHostException

class AppException : Exception {
    var errorMsg: String

    constructor(error: String?) : super(error) {
        errorMsg = error ?: parseError(null)
    }

    constructor(throwable: Throwable?) : super(throwable) {
        errorMsg = parseError(throwable)
    }

    private fun parseError(throwable: Throwable?): String {
        throwable?.printStackTrace()
        return throwable.parseErrorString()
    }
}

fun Throwable?.parseErrorString(): String {
    return when (this) {
        is ConnectException -> MyApp.instance.getString(R.string.ConnectException)
        is UnknownHostException -> MyApp.instance.getString(R.string.UnknownHostException)
        else -> MyApp.instance.getString(R.string.ElseNetException)
    }
}