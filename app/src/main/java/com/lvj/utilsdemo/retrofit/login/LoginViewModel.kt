package com.lvj.utilsdemo.retrofit.login

import androidx.lifecycle.MutableLiveData
import com.lvj.utilsdemo.retrofit.api.UserEntity
import com.lvj.utilsdemo.retrofit.ext.BaseViewModel
import com.lvj.utilsdemo.retrofit.ext.VmLiveData
import com.lvj.utilsdemo.retrofit.ext.launchVmRequest

class LoginViewModel : BaseViewModel() {

    private val loginRepository by lazy { LoginRepository() }


    val loginResult: VmLiveData<UserEntity> = MutableLiveData()

    fun getUserInfo(userName: String, password: String, param: Any? = null) {
        launchVmRequest({ loginRepository.login(userName, password) }, loginResult, param)
    }

}

