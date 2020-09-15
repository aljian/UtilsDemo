package com.lvj.utilsdemo.retrofit.login

import com.lvj.utilsdemo.retrofit.api.BaseEntity
import com.lvj.utilsdemo.retrofit.api.NetworkApi
import com.lvj.utilsdemo.retrofit.api.UserEntity

class LoginRepository {

    suspend fun login(userName: String, password: String): BaseEntity<UserEntity> {
        return NetworkApi.getApi().login(userName, password)
    }
}