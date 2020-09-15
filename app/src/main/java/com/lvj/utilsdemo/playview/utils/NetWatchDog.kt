package com.lvj.utilsdemo.playview.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.lvj.utilsdemo.util.logi

class NetWatchDog(private val mContext: Context) {

    private var mNetChangeListener: NetChangeListener? = null
    private var mNetConnectedListener: NetConnectedListener? = null
    private var isReconnect = false

    //广播过滤器，监听网络变化
    private val mNetIntentFilter = IntentFilter()

    init {
        mNetIntentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
    }

    fun startWatch() {
        try {
            mContext.applicationContext.registerReceiver(mReceiver, mNetIntentFilter)
        } catch (e: Exception) {

        }
    }

    fun stopWatch() {
        try {
            mContext.applicationContext.unregisterReceiver(mReceiver)
        } catch (e: Exception) {

        }
    }

    fun setOnNetChangeListener(l: NetChangeListener) {
        this.mNetChangeListener = l
    }

    fun setNetConnectedListener(l: NetConnectedListener) {
        this.mNetConnectedListener = l
    }

    private val mReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val wifiNetworkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            val mobileNetworkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            val activeNetworkInfo = cm.activeNetworkInfo

            var wifiState = NetworkInfo.State.UNKNOWN
            var mobileState = NetworkInfo.State.UNKNOWN

            if (wifiNetworkInfo != null) {
                wifiState = wifiNetworkInfo.state;
            }
            if (mobileNetworkInfo != null) {
                mobileState = mobileNetworkInfo.state;
            }

            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting) {
                if (mNetConnectedListener != null) {
                    mNetConnectedListener!!.onReNetConnected(isReconnect)
                    isReconnect = false
                    logi("onReNetConnected")
                }
            } else if (activeNetworkInfo == null) {
                if (mNetConnectedListener != null) {
                    isReconnect = true
                    mNetConnectedListener!!.onNetUnConnected()
                    logi("onNetUnConnected")
                }
            }

            if (NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED == mobileState) {
                logi("onChangeTo4G")
                mNetChangeListener?.onChangeTo4G()
            } else if (NetworkInfo.State.CONNECTED == wifiState && NetworkInfo.State.CONNECTED != mobileState) {
                logi("onChangeToWifi")
                mNetChangeListener?.onChangeToWifi()
            } else if (NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED != mobileState) {
                logi("onNetDisconnected")
                mNetChangeListener?.onNetDisconnected()
            }

        }

    }


    interface NetChangeListener {
        /**
         * 变成4G网络
         */
        fun onChangeTo4G()

        /**
         * 变为wifi
         */
        fun onChangeToWifi()

        /**
         * 网络断开
         */
        fun onNetDisconnected()
    }


    interface NetConnectedListener {

        /**
         * 网络已连接
         */
        fun onReNetConnected(isReconnect: Boolean)

        /**
         * 网络未连接
         */
        fun onNetUnConnected()
    }
}

fun Context.is4GConnected(): Boolean {
    var result = false
    val manager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    runCatching {
        val mobileNetworkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val mobileState = mobileNetworkInfo.state
        NetworkInfo.State.CONNECTED == mobileState
    }.onSuccess {
        result = it
    }.onFailure {
        result = false
    }
    return result
}
