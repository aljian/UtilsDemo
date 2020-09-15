package com.lvj.utilsdemo.retrofit.ext

sealed class VmState<out T> {
    object Loading : VmState<Nothing>()
    data class Success<out T>(val data: T, val param: Any?) : VmState<T>()
    object Relogin : VmState<Nothing>()
    data class Error(val error: AppException) : VmState<Nothing>()
}

class VmResult<T> {
    var onAppLoading: () -> Unit = {}
    var onAppSuccess: (data: T, param: Any?) -> Unit = { data, param -> }
    var onReLogin: () -> Unit = { }
    var onAppError: (AppException) -> Unit = {}
    var onAppComplete: () -> Unit = {}
}