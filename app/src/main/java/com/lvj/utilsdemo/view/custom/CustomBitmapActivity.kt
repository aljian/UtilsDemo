package com.lvj.utilsdemo.view.custom

import android.os.Bundle
import android.text.Editable
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.R
import org.xml.sax.XMLReader

class CustomBitmapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_bitmap)




        Html.fromHtml("", null, object : Html.TagHandler {
            override fun handleTag(opening: Boolean, tag: String, output: Editable, xmlReader: XMLReader) {
                mListener?.invoke(0)
            }

        })

        setListener {

        }
    }

    var mListener: ((Int) -> Unit)? = null

    fun setListener(listener: (Int) -> Unit) {
        this.mListener = listener
    }


}