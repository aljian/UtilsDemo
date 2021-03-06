package com.lvj.utilsdemo.retrofit.login

import android.os.Bundle
import androidx.activity.viewModels
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.retrofit.ext.vmObserver
import com.lvj.utilsdemo.util.logi
import kotlinx.android.synthetic.main.activity_layout_login.*

class LoginActivity : BaseActivity() {

    private val mViewModel by viewModels<LoginViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_login)

        et_userName.setText("aljian")
        et_password.setText("Al070633")

        tv_login.setOnClickListener {
            val name = et_userName.text
            val pw = et_password.text
            mViewModel.getUserInfo(name.toString(), pw.toString())
        }

        tv_login2.setOnClickListener {
            val name = et_userName.text
            val pw = et_password.text
            mViewModel.getUserInfo(name.toString(), pw.toString(), 1)
        }

        mViewModel.loginResult.vmObserver(this) {
            onAppLoading = { logi("loading") }
            onAppError = { logi("error = ${it.errorMsg}") }
            onAppSuccess = { data, param ->
                logi("success param = ${param}")
            }
            onAppComplete = { logi("complete") }
            onReLogin = { logi("relogin") }
        }


    }

}