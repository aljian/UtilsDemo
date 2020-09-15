package com.lvj.utilsdemo.audio

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.logi
import kotlinx.android.synthetic.main.activity_layout_music.*

class MusicActivity : AppCompatActivity() {

    private lateinit var mService: MyService
    private var mBound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_music)

        tv_music.text = "MusicActivity"
        tv_music.setOnClickListener {
            Intent(this, MyService::class.java).also { intent ->
                stopService(intent)
            }
            if (mBound) unbindService(connection)
        }

        Intent(this, MyService::class.java).also { intent ->
            startService(intent)
        }

        Intent(this, MyService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

        tv_jump.setOnClickListener {
            startActivity(Intent(this, MusicActivity2::class.java))
        }
    }


    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            logi("onServiceConnected $this")
            val binder = service as MyService.PlayBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            logi("onServiceDisconnected $this")
            mBound = false
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (mBound) unbindService(connection)
    }

}