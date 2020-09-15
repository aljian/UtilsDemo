package com.lvj.utilsdemo.textspan

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.Html.ImageGetter
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.Test
import com.lvj.utilsdemo.util.logi
import com.lvj.utilsdemo.util.toast
import kotlinx.android.synthetic.main.layout_rich_text.*
import org.xml.sax.XMLReader
import java.util.*

class RichTextActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_rich_text)

        val ss = StringBuffer()
        for (i in 0 until 1000) {
            ss.append("哈哈$i")
        }

        tv_selected.text = Html.fromHtml(Test.html_demo, MyImageGetter(this), MyTagHandler())

        tv_selected.setActionItemClicked { position ->
//            logi("selected pos = $position")
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
        val subSequence = ss.subSequence(tv_selected.selectionStart, tv_selected.selectionEnd)
        if (subSequence.contains("<img")) {

        }

        val span = CustomReplaceSpan()
        ss.setSpan(
            span,
            tv_selected.selectionStart,
            tv_selected.selectionEnd,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        tv_selected.text = ss
    }


    class MyImageGetter(var mContext: Context) : ImageGetter {

        override fun getDrawable(source: String?): Drawable {
            logi("getDrawable source = $source")
            val drawable = ContextCompat.getDrawable(mContext, R.drawable.emoji_2)!!
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            return drawable
        }

    }


    class MyTagHandler : Html.TagHandler {

        override fun handleTag(opening: Boolean, tag: String?, output: Editable?, xmlReader: XMLReader?) {
            if (opening) {
                return
            }
//            logi("handleTag tag = ${tag?.toLowerCase(Locale.getDefault())}")
            if ("img" == tag?.toLowerCase(Locale.getDefault())) {
                val len = output!!.length
                logi("handleTag output = ${output.toString()}")
                val spans = output.getSpans(len - 1, len, ImageSpan::class.java)
                if (spans.isNullOrEmpty()) {
                    logi("handleTag spans isNullOrEmpty")
                    return
                }
                spans.forEach {
                    logi("handleTag source = ${it.source}")
                }
            }
        }

    }


}