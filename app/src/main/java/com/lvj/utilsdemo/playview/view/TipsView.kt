package com.lvj.utilsdemo.playview.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

class TipsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private var mNetChangeTipView: NetChangeTipView? = null

    fun showNetChangeTipView() {
        if (mNetChangeTipView == null) {
            mNetChangeTipView = NetChangeTipView(context)
            addSubView(mNetChangeTipView!!)
        }

        if (mNetChangeTipView!!.visibility != VISIBLE) {
            mNetChangeTipView!!.visibility = VISIBLE
        }

    }


    /**
     * 添加控件到容器中
     */
    private fun addSubView(view: View) {
        val params = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.CENTER
        addView(view)
    }


}