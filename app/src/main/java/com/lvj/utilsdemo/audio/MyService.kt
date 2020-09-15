package com.lvj.utilsdemo.audio

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.wifi.WifiManager
import android.os.Binder
import android.os.IBinder
import android.os.PowerManager
import com.lvj.utilsdemo.audio_url1
import com.lvj.utilsdemo.util.logi

const val ACTION_PLAY = "com.lvj.action.play"
const val ACTION_PAUSE = "com.lvj.action.pause"


class MyService : Service(), MediaPlayer.OnPreparedListener {

    private var mMediaPlayer: MediaPlayer? = null
    private var wifiLock: WifiManager.WifiLock? = null
    private val binder = PlayBinder()


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        logi("onStartCommand $this")
        val action: String? = intent.action
        when (action) {
            ACTION_PLAY -> {
                mMediaPlayer = MediaPlayer()
                mMediaPlayer?.apply {
                    setDataSource(audio_url1)
                    setOnPreparedListener(this@MyService)
                    setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
                    wifiLock()
                    prepareAsync()
                }
            }
            else -> {

            }
        }
        return START_STICKY
    }

    inner class PlayBinder : Binder() {
        fun getService(): MyService = this@MyService
    }


    override fun onCreate() {
        super.onCreate()
        logi("onCreate $this")
    }

    override fun onBind(intent: Intent?): IBinder? {
        logi("onBind $this")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        logi("onUnbind $this")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        logi("onDestroy $this")
        super.onDestroy()
        mMediaPlayer?.release()
        releaseWifiLock()
    }


    override fun onPrepared(mp: MediaPlayer) {
        mp.start()
    }

    private fun wifiLock() {
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiLock = wifiManager.createWifiLock(WifiManager.WIFI_MODE_FULL, "mylock")
        wifiLock?.acquire()
    }

    private fun releaseWifiLock() {
        wifiLock?.release()
    }

}