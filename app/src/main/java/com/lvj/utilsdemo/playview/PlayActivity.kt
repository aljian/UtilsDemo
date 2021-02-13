package com.lvj.utilsdemo.playview

import android.content.res.Configuration
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.*
import kotlinx.android.synthetic.main.activity_layout_play.*

class PlayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_play)

        playView.setPlayActionListener(object : PlayActionListener {

            override fun onBackClick() {
                logi("activity 收到 back 事件")
            }

            override fun orientationChange(from: Boolean, currentMode: ScreenMode) {

            }

        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        logi("onConfigurationChanged")
//        updatePlayerView()
    }

    override fun onResume() {
        super.onResume()
        logi("onResume")
        updatePlayerView()

        playView.showBar(this)
        playView.onResume()
    }

    override fun onStop() {
        super.onStop()
        playView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        playView.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val playEvnet = playView.onKeyDown(keyCode, event)
        if (!playEvnet) {
            return false
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun updatePlayerView() {
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            val layoutParams = playView.layoutParams
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = getScreenWidthPx() * 9 / 16
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val layoutParams = playView.layoutParams
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        }
    }
}