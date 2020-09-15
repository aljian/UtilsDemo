package com.lvj.utilsdemo.pad

import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.isTablet
import com.lvj.utilsdemo.util.logi

class TabletPhoneActivity : AppCompatActivity() {

    private var is_tablet = false

    private var tabletFragment: TabletFragment? = null
    private var phoneFragment: PhoneFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_phone)
        is_tablet = resources.getBoolean(R.bool.is_tablet)

        logi("isTablet = ${isTablet()}")
        initView()
    }

    private fun initView() {

        val transaction = supportFragmentManager.beginTransaction()
        if (is_tablet) {
            tabletFragment = TabletFragment()
            transaction.add(R.id.fl_container, tabletFragment!!)
        } else {
            phoneFragment = PhoneFragment()
            transaction.add(R.id.fl_container, phoneFragment!!)
        }
        transaction.commitNow()
    }

}

