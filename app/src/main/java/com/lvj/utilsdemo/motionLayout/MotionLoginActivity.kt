package com.lvj.utilsdemo.motionLayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.logi
import com.lvj.utilsdemo.util.toast
import kotlinx.android.synthetic.main.activity_motion_login.*

class MotionLoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_login)

        motion_login.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                logi("Trigger p1 = $p1   p2 = $p2")
                runOnUiThread {
                    progress_login.visibility = if (p2) View.VISIBLE else View.GONE
//                    if (progress_login.visibility == View.VISIBLE) {
//                        progress_login.visibility = View.GONE
//                    } else {
//                        progress_login.visibility = View.VISIBLE
//                    }
                }
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                logi("Started p1 = $p1   p2 = $p2")
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                logi("Started p3 == 1.0f ${p3 == 1.0f}")
                runOnUiThread {
                    progress_login.visibility = if (p3 == 1.0f) View.VISIBLE else View.GONE
                }
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                logi("Completed p1 = $p1  ")
                if (p1 == R.id.login_end) {
                    toast("完成")
                }
            }
        })

        btn_reset.setOnClickListener {
            motion_login.transitionToState(R.id.login_start)


        }
    }

}