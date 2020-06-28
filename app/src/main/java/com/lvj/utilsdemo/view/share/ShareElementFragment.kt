package com.lvj.utilsdemo.view.share

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.lvj.utilsdemo.R

class ShareElementFragment : Fragment() {

    companion object {

        fun newInstance(rect: Rect): ShareElementFragment {
            val args = Bundle()
            val fragment = ShareElementFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var parentLoc: IntArray? = null
    private var startX = 0f
    private var startY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val trans = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        trans.duration = 1500L
        sharedElementEnterTransition = trans
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_share_element, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


}