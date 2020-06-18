package com.lvj.utilsdemo.view.anim

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.R
import kotlinx.android.synthetic.main.activity_animation_object.*

class AnimationObjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_object)

        btn_start.setOnClickListener {
            doObjectAnimation()
        }

        btn_iv_alert.setOnClickListener {
            doAlertAnimator()
        }

    }


    private fun doAlertAnimator() {
//        iv_alert
        val frame0 = Keyframe.ofFloat(0f, 0f)
        val frame1 = Keyframe.ofFloat(0.1f, -20f)
        val frame2 = Keyframe.ofFloat(0.2f, 20f)
        val frame3 = Keyframe.ofFloat(0.3f, -20f)
        val frame4 = Keyframe.ofFloat(0.4f, 20f)
        val frame5 = Keyframe.ofFloat(0.5f, -20f)
        val frame6 = Keyframe.ofFloat(0.6f, 20f)
        val frame7 = Keyframe.ofFloat(0.7f, -20f)
        val frame8 = Keyframe.ofFloat(0.8f, 20f)
        val frame9 = Keyframe.ofFloat(0.9f, -20f)
        val frame10 = Keyframe.ofFloat(1f, 0f)

        val rotateHolder = PropertyValuesHolder.ofKeyframe(
            "rotation"
            , frame0, frame1, frame2, frame3, frame4, frame5,
            frame6, frame7, frame8, frame9, frame10
        )
        val animator = ObjectAnimator.ofPropertyValuesHolder(iv_alert, rotateHolder)
        animator.duration = 1000
        animator.start()
    }


    private fun doObjectAnimation() {

        val rotateHolder = PropertyValuesHolder.ofFloat("Rotation", 60f, -60f, 40f, -40f, -20f, 20f, 10f, -10f, 0f)
        val colorHolder = PropertyValuesHolder.ofInt("BackgroundColor", -0x1, -0xff01, -0x100, -0x1)

        val animator = ObjectAnimator.ofPropertyValuesHolder(tv_animation, rotateHolder, colorHolder)
        animator.duration = 3000
        animator.interpolator = AccelerateInterpolator()
        animator.start()
    }

}
