package com.lvj.utilsdemo

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.lvj.utilsdemo.audio.MusicActivity
import com.lvj.utilsdemo.constraint.ConstraintLayoutActivity
import com.lvj.utilsdemo.dialog.AlertDialogActivity
import com.lvj.utilsdemo.fragment.FragmentDemoActivity
import com.lvj.utilsdemo.motionLayout.MotionLayoutActivity
import com.lvj.utilsdemo.motionLayout.MotionLoginActivity
import com.lvj.utilsdemo.motionLayout.MotionVpActivity
import com.lvj.utilsdemo.playview.PlayActivity
import com.lvj.utilsdemo.retrofit.login.LoginActivity
import com.lvj.utilsdemo.retrofit.ui.HomeArticleActivity
import com.lvj.utilsdemo.rvcard.RvCardActivity
import com.lvj.utilsdemo.textspan.RichTextActivity
import com.lvj.utilsdemo.util.*
import com.lvj.utilsdemo.view.anim.AnimationActivity
import com.lvj.utilsdemo.view.behavior.DragViewActivity
import com.lvj.utilsdemo.view.share.ShareElementActivity
import com.lvj.utilsdemo.vp.VpActivity
import com.lvj.utilsdemo.work.WorkDemoActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_test.shape = shape {
            corner_radius = 10
            gradient_colors = listOf("#ff00ff", "#800000ff")
            shape = GradientDrawable.RECTANGLE
            strokeAttr = Stroke(4, "#000000")
        }

        tv_test.setOnClickListener {
            startActivity(Intent(this, RVDemoActivity::class.java))
//            startActivity(Intent(this, WebActivity::class.java))
        }

        btn_large.setOnClickListener {

            startActivity(Intent(this, VpActivity::class.java))
//            startActivity(Intent(this, TabletPhoneActivity::class.java))
        }

        btn_work.setOnClickListener {
            startActivity(Intent(this, WorkDemoActivity::class.java))
        }

        btn_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btn_play.setOnClickListener {
            startActivity(Intent(this, PlayActivity::class.java))
        }

        btn_constraintLayout.setOnClickListener {
            startActivity(Intent(this, ConstraintLayoutActivity::class.java))
        }
        btn_dialog.setOnClickListener {
            startActivity(Intent(this, AlertDialogActivity::class.java))
        }
        btn_anim.setOnClickListener {
            startActivity(Intent(this, AnimationActivity::class.java))
        }

        btn_dragView.setOnClickListener {
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
        btn_richtext.setOnClickListener {
            startActivity(Intent(this, RichTextActivity::class.java))
        }
        btn_music.setOnClickListener {
            startActivity(Intent(this, MusicActivity::class.java))
        }


//        setHtmlText()
        setSpanText()

        val img_url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2020089733,86807406&fm=26&gp=0.jpg"

        GlideApp.with(this).load(img_url).circleCrop().into(iv_main)


        logi("15dp     = ${1.dp}")
        logi("15dpTopx = ${dpTopx(1f)}")
        logi("15dp2px  = ${dp2px(1)}")

        logi("屏高 getScreenHeightPx =  ${getScreenHeightPx()}")
        logi("屏高 getHeight =  ${getHeightPx()}")

        logi("freeSpace = ${filesDir.freeSpace / (1024 * 1024 * 1024F)}")
        logi("totalSpace = ${filesDir.totalSpace / (1024 * 1024 * 1024F)}")


//        dolaunch()

//        getFirstInstallTime()

        slider.addOnChangeListener { slider, value, fromUser ->
            logi("value = $value")
        }

    }

    private fun getFirstInstallTime() {

        val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS", Locale.getDefault())
        logi("时间 ${format.format(Date())}")

        val s = packageManager.getPackageInfo("android", 0).firstInstallTime
        val a = packageManager.getPackageInfo(packageName, 0).firstInstallTime
        logi("s = $s")
        logi("a = $a")
    }


    private fun dolaunch() {
        lifecycleScope.launch {
            logi("current thread = ${Thread.currentThread().name}")
            withContext(Dispatchers.IO) {
                logi("current thread = ${Thread.currentThread().name}")
            }
            logi("current thread = ${Thread.currentThread().name}")
        }

    }

    private fun setSpanText() {
        val ssb = SpannableStringBuilder()
        for (i in 0 until 10) {
            val time = "Time $i"
            val name = "Name $i"
            val text = "哈哈哈哈哈哈哈哈哈哈 $i"

            val spanTime = SpannableString(time)
            spanTime.setSpan(ForegroundColorSpan(Color.RED), 0, time.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

            val spanText = SpannableString(text)
            spanText.setSpan(ForegroundColorSpan(Color.BLUE), 0, text.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

            ssb.append(spanTime).append(name).append(spanText).append("\u3000\u3000\n")
        }
        tv_main.text = ssb

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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        logi("MainActivity onConfigurationChanged")
    }
}
