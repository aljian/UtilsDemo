package com.lvj.utilsdemo.retrofit.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lvj.utilsdemo.retrofit.api.BaseEntity
import com.lvj.utilsdemo.retrofit.api.UserEntity
import com.lvj.utilsdemo.retrofit.ext.*
import com.lvj.utilsdemo.util.logi
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.UnknownHostException

class LoginViewModel : BaseViewModel() {

    private val loginRepository by lazy { LoginRepository() }


    val loginResult: VmLiveData<UserEntity> = MutableLiveData()

    fun getUserInfo(userName: String, password: String, param: Any? = null) {
        launchVmRequest({ loginRepository.login(userName, password) }, loginResult, param)
    }

}

