package com.lvj.utilsdemo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.constraint.ConstraintLayoutActivity
import com.lvj.utilsdemo.dialog.AlertDialogActivity
import com.lvj.utilsdemo.fragment.FragmentDemoActivity
import com.lvj.utilsdemo.motionLayout.MotionLayoutActivity
import com.lvj.utilsdemo.motionLayout.MotionLoginActivity
import com.lvj.utilsdemo.motionLayout.MotionVpActivity
import com.lvj.utilsdemo.retrofit.ui.HomeArticleActivity
import com.lvj.utilsdemo.view.anim.AnimationActivity
import com.lvj.utilsdemo.view.behavior.DragViewActivity
import com.lvj.utilsdemo.view.share.ShareElementActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_constraintLayout.setOnClickListener {
//            startActivity(Intent(this, DemoActivity::class.java))
            startActivity(Intent(this, ConstraintLayoutActivity::class.java))
        }
        btn_dialog.setOnClickListener {
            startActivity(Intent(this, AlertDialogActivity::class.java))
        }
        btn_anim.setOnClickListener {
            startActivity(Intent(this, AnimationActivity::class.java))
        }

        btn_behavior.setOnClickListener {
            startActivity(Intent(this, DragViewActivity::class.java))
        }

        btn_shareElement.setOnClickListener {
            startActivity(Intent(this, ShareElementActivity::class.java))
        }

        btn_fragment.setOnClickListener {
            startActivity(Intent(this, FragmentDemoActivity::class.java))
        }

        btn_motion.setOnClickListener {
            startActivity(Intent(this, MotionLayoutActivity::class.java))
        }

        btn_motion_vp.setOnClickListener {
            startActivity(Intent(this, MotionVpActivity::class.java))
        }

        btn_motion_login.setOnClickListener {
            startActivity(Intent(this, MotionLoginActivity::class.java))
        }

        btn_retrofit.setOnClickListener {
            startActivity(Intent(this, HomeArticleActivity::class.java))
        }

        setHtmlText()

        val img_url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2020089733,86807406&fm=26&gp=0.jpg"

        GlideApp.with(this).load(img_url).circleCrop().into(iv_main)

    }

    private fun setHtmlText() {
        val a = "hello"
        val b = "world"

        val ss = SpannableStringBuilder().append(a).append(b)
        val f = ForegroundColorSpan(Color.parseColor("#FF0000"))
        ss.setSpan(f, 0, a.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        val s = AbsoluteSizeSpan(30, true)
        ss.setSpan(s, 0, a.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        val f2 = ForegroundColorSpan(Color.parseColor("#00FF00"))
        ss.setSpan(f2, ss.length - b.length, ss.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        val s2 = AbsoluteSizeSpan(10, true)
        ss.setSpan(s2, ss.length - b.length, ss.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        tv_main.text = ss
    }
}
