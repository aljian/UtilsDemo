package com.lvj.utilsdemo.view.anim

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.toast
import kotlinx.android.synthetic.main.activity_animation_xml.*

class AnimationXmlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_xml)


        //tv_do
        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_scale)
        val setAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_set)
        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate)
        val translateAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_translate)

        tv_do.startAnimation(scaleAnimation)
        tv_do.setOnClickListener {
            toast("哈哈哈哈")
        }

        btn_scale.setOnClickListener {
            tv_do.startAnimation(scaleAnimation)
        }
        btn_set.setOnClickListener {
            tv_do.startAnimation(setAnimation)
        }
        btn_rotate.setOnClickListener {
            tv_do.startAnimation(rotateAnimation)
        }
        btn_translate.setOnClickListener {
            tv_do.startAnimation(translateAnimation)
        }


    }

}
