package com.lvj.utilsdemo.motionLayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.toast
import kotlinx.android.synthetic.main.motion_animationset.*

class MotionLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_motion_layout)
//        setContentView(R.layout.motion_tran_vertical)
//        setContentView(R.layout.motion_color_change)
        setAnimationSet()
    }

    private fun setAnimationSet() {
        setContentView(R.layout.motion_animationset)
        val sb = StringBuilder("开始\n")
        for (i in 0 until 2000) {
            sb.append(" 哈哈$i")
        }
        sb.append("\n结束")
        tv_motion.text = sb

        tv_motion.setOnClickListener {
            toast("点击了文字")
        }
        iv_1.setOnClickListener {
            toast("1111")
        }
        iv_2.setOnClickListener {
            toast("2222")
        }
        iv_3.setOnClickListener {
            toast("3333")
        }
        iv_4.setOnClickListener {
            toast("4444")
        }
    }


}