package com.lvj.utilsdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.loge
import com.lvj.utilsdemo.util.logi
import com.shuyu.gsyvideoplayer.GSYVideoManager
import kotlinx.android.synthetic.main.fragment_tab_demo.*

class TabDemoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_demo, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("title")
        tv_demo.text = title
        logi("onViewCreated ${this.tag} --title = $title")
    }

    override fun onResume() {
        super.onResume()
        logi("onResume ${this.tag}")
    }


    override fun onPause() {
        super.onPause()
        logi("onPause ${this.tag}")
    }

    override fun onStart() {
        super.onStart()
        logi("onStart ${this.tag}")
    }

    override fun onStop() {
        super.onStop()
        loge("onStop ${this.tag}")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        loge("onDestroyView ${this.tag}")
    }

    override fun onDestroy() {
        super.onDestroy()
        loge("onDestroy ${this.tag}")

        GSYVideoManager.releaseAllVideos()
    }


}