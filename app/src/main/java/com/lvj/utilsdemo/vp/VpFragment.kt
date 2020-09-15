package com.lvj.utilsdemo.vp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.logi
import kotlinx.android.synthetic.main.fragment_vp.*

class VpFragment : Fragment() {

    companion object {
        fun newInstance(param: String): VpFragment {
            val args = Bundle()
            args.putString("param", param)
            val fragment = VpFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var position: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logi("onCreateView $this")
        return inflater.inflate(R.layout.fragment_vp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        position = arguments?.getString("param")
        logi("onViewCreated $position $this")
        frag_tv.text = position
    }

    override fun onResume() {
        logi("onResume $position $this")
        super.onResume()
    }

    override fun onDestroyView() {
        logi("onDestroyView $position $this")
        super.onDestroyView()
    }

    override fun onDestroy() {
        logi("onDestroy $position $this")
        super.onDestroy()
    }
}