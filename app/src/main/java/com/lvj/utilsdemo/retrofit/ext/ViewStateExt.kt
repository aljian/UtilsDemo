package com.lvj.utilsdemo.retrofit.ext

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.lvj.utilsdemo.retrofit.api.BaseEntity
import kotlinx.coroutines.launch

fun <T> BaseViewModel.launchVmRequest(
    request: suspend () -> BaseEntity<T>,
    viewState: VmLiveData<T>,
    param: Any? = null
) =
    viewModelScope.launch {
        runCatching {
            viewState.value = VmState.Loading
            request()
        }.onSuccess {
            viewState.paresVmResult(it, param)
        }.onFailure {
            viewState.paresVmException(it)
        }
    }



/**
 * 处理返回值
 *
 * @param result 请求结果
 */
fun <T> VmLiveData<T>.paresVmResult(result: BaseEntity<T>, param: Any?) {
    value = when (result.errorCode) {
        HTTP_RESULT_OK -> {
            VmState.Success(result.data, param)
        }
        HTTP_RESULT_RELOGIN -> {
            VmState.Relogin
        }
        else -> {
            VmState.Error(AppException(result.errorMsg))
        }
    }
}

/**
 * 异常转换异常处理
 */
fun <T> VmLiveData<T>.paresVmException(e: Throwable) {
    this.value = VmState.Error(AppException(e))
}

@MainThread
inline fun <T> VmLiveData<T>.vmObserver(owner: LifecycleOwner, vmResult: VmResult<T>.() -> Unit) {
    val result = VmResult<T>();result.vmResult();observe(owner) {
        when (it) {
            is VmState.Loading -> {
                result.onAppLoading()
            }
            is VmState.Success -> {
                result.onAppSuccess(it.data, it.param)
                result.onAppComplete()
            }
            is VmState.Error -> {
                result.onAppError(it.error)
                result.onAppComplete()
            }
            is VmState.Relogin -> {
                result.onReLogin()
                result.onAppComplete()
            }
        }
    }
}