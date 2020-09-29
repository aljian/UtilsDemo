package com.lvj.utilsdemo.playview.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.createRectDrawable
import com.lvj.utilsdemo.util.logi
import kotlinx.android.synthetic.main.play_view_netchangetip.view.*

class NetChangeTipView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {


    private val drawable by lazy { context.createRectDrawable() }

    init {
        LayoutInflater.from(context).inflate(R.layout.play_view_netchangetip, this, true)
        setListener()
    }

    private fun setListener() {
        play_netchange_title.text = "网络变化的TipsView"
        play_netchange_confirm.text = "确定"
        play_netchange_confirm.background = drawable

        play_netchange_confirm.setOnClickListener {
            logi("网络变化TipView")
        }
    }


}