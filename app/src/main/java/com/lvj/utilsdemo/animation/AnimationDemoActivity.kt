package com.lvj.utilsdemo.animation

import android.os.Bundle
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.toast
import kotlinx.android.synthetic.main.activity_animation_demo.*

class AnimationDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_demo)

        tv_start.setOnClickListener {
//            ani1()
            ani2()
        }

        tv_ani.setOnClickListener {
            toast("click")
        }
    }

    private fun ani1() {
        val tran = TranslateAnimation(0f, 150f, 0f, 300f)
        tran.duration = 500
        tran.fillAfter = true
        tv_ani.startAnimation(tran)
    }

    private fun ani2() {
        tv_ani.animate().translationX(150f).translationY(300f).setDuration(500).start()
    }

}