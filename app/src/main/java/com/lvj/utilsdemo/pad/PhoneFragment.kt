package com.lvj.utilsdemo.pad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.logi
import kotlinx.android.synthetic.main.fragment_tablet.*

class PhoneFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tablet, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mTitle = arrayOf("tab1", "tab2", "tab3", "tab4")

        for (i in mTitle.indices) {
            tab_tablet.addTab(tab_tablet.newTab(), i == 0)
            tab_tablet.getTabAt(i)?.text = mTitle[i]
        }

        tab_tablet.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                logi("onTabSelected  tab = ${tab == null}")
                tab?.let {
                    logi("onTabSelected  tab position = ${it.position}")
                    tv_test.text = mTitle[it.position]
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        tv_test.text = mTitle[0]
    }
}