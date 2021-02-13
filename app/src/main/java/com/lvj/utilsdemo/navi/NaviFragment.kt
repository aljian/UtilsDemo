package com.lvj.utilsdemo.navi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.lvj.utilsdemo.R
import kotlinx.android.synthetic.main.fragment_navi1.*

class NaviFragment : Fragment() {

    private var pos = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_navi1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            pos = it.getInt("pos", 0)
        }

        tv_f1.text = "pos = $pos"

        tv_f1.setOnClickListener {
            when (pos) {
                0 -> {
                    val bundle = Bundle()
                    bundle.putInt("pos", 1)
                    Navigation.findNavController(requireView()).navigate(R.id.action_frag1_to_frag2, bundle)
                }
                1 -> {
                    val bundle = Bundle()
                    bundle.putInt("pos", 2)
                    Navigation.findNavController(requireView()).navigate(R.id.action_frag2_to_frag3, bundle)
                }
                2 -> {
                    val bundle = Bundle()
                    bundle.putInt("pos", 0)
                    Navigation.findNavController(requireView()).navigate(R.id.action_frag3_to_frag1, bundle)
                }
            }
        }
    }
}