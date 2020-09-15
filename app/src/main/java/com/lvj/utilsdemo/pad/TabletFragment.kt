package com.lvj.utilsdemo.pad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.lvj.utilsdemo.R
import kotlinx.android.synthetic.main.fragment_tablet.*

class TabletFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tablet, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mTitle = arrayOf("tab1", "tab2", "tab3", "tab4")

        mTitle.forEach { _ ->
            tab_tablet.addTab(tab_tablet.newTab())
        }

        for (i in mTitle.indices) {
            tab_tablet.getTabAt(i)?.text = mTitle[i]
        }

        tab_tablet.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    tv_test.text = mTitle[it.position]
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        tab_tablet.getTabAt(0)?.select()
    }
}