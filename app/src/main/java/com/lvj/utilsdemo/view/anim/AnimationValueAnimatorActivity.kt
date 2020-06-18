package com.lvj.utilsdemo.view.anim

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.logi
import com.lvj.utilsdemo.util.toast
import kotlinx.android.synthetic.main.activity_animation_value.*

class AnimationValueAnimatorActivity : AppCompatActivity() {

    private var doAnimationRepeat: ValueAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_value)

        tv_test.setOnClickListener {
            toast("哈哈哈")
        }
        btn_start.setOnClickListener {
            toast("一个普通的动画")
            doAnimationRepeat?.cancel()
            doAnimation()
        }


        btn_start_repeat.setOnClickListener {
            toast("重复动画")
            doAnimationRepeat?.cancel()
            doAnimationRepeat = doAnimationRepeat()
        }
        btn_cancel.setOnClickListener {
            doAnimationRepeat?.cancel()
        }

        tv_inter.setOnClickListener {
            doAnimationRepeat?.cancel()
            toast("平移+弹跳插值器")
            doAnimationInter()
        }

        tv_custom_inter.setOnClickListener {
            doAnimationRepeat?.cancel()
            toast("平移+自定义插值器")
            doAnimationCustomInter()
        }
    }

    private fun doAnimationCustomInter() {
        val animator = ValueAnimator.ofInt(0, 600)
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            tv_test.layout(tv_test.left, curValue, tv_test.right, curValue + tv_test.height)
        }
        animator.duration = 1000
        animator.interpolator = MyInterploator()
        animator.start()
    }


    private fun doAnimationInter() {
        val animator = ValueAnimator.ofInt(0, 600)
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            tv_test.layout(tv_test.left, curValue, tv_test.right, curValue + tv_test.height)
        }
        animator.duration = 1000
        animator.interpolator = BounceInterpolator()
        animator.start()

    }


    private fun doAnimationRepeat(): ValueAnimator {
        val animator = ValueAnimator.ofInt(0, 400)
        animator.duration = 1000
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            tv_test.layout(tv_test.left, curValue, tv_test.right, curValue + tv_test.height)
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                logi("onAnimationRepeat")
            }

            override fun onAnimationEnd(animation: Animator?) {
                logi("onAnimationEnd")
            }

            override fun onAnimationCancel(animation: Animator?) {
                logi("onAnimationCancel")
            }

            override fun onAnimationStart(animation: Animator?) {
                logi("onAnimationStart")
            }

        })


        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = ValueAnimator.INFINITE
        animator.start()
        return animator
    }


    private fun doAnimation() {
        val animator = ValueAnimator.ofInt(0, 400, 50, 300)
        animator.duration = 3000
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            tv_test.layout(curValue, curValue, curValue + tv_test.width, curValue + tv_test.height)
        }
        animator.start()
    }


    inner class MyInterploator : TimeInterpolator {
        override fun getInterpolation(input: Float) = 1 - input
    }

}