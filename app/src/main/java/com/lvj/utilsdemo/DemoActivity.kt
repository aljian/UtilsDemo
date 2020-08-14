package com.lvj.utilsdemo

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.util.logi
import com.lvj.utilsdemo.util.toast
import kotlinx.android.synthetic.main.layout_demo.*

class DemoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_demo)

//        btn_demo.setOnClickListener {
//            if (progress_login.visibility == View.VISIBLE) {
//                progress_login.visibility = View.GONE
//            } else {
//                progress_login.visibility = View.VISIBLE
//            }
//        }

        val ss = StringBuffer()
        for (i in 0 until 1000) {
            ss.append("哈哈$i")
        }

        tv_selected.text = ss

        tv_selected.setActionItemClicked { position ->
            logi("selected pos = $position")
            logi(
                "选择的文字 = ${tv_selected.text.toString()
                    .substring(tv_selected.selectionStart, tv_selected.selectionEnd)}"
            )
            when (position) {
                0 -> {
                    toast("复制")
                }
                1 -> {
                    setUnderlineSpan()
                }
                else -> {
                }
            }
        }

    }

    private fun setUnderlineSpan() {
        val ss = SpannableString(tv_selected.text)
        ss.setSpan(
            UnderlineSpan(),
            tv_selected.selectionStart,
            tv_selected.selectionEnd,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        tv_selected.text = ss
    }
}