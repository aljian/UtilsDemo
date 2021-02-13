package com.lvj.utilsdemo.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.lvj.utilsdemo.R
import kotlinx.android.synthetic.main.activity_fragement_demo.*

class FragmentDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragement_demo)

        val mTitle = mutableListOf<String>()

        for (i in 0 until 10) {
            mTitle.add("title ${i + 1}")
        }

        val mAdapter = MyFragmentAdapter(mTitle, this)
        frag_vp.adapter = mAdapter

        TabLayoutMediator(frag_tab, frag_vp) { tab, position ->
            tab.text = mTitle[position]
        }.attach()



        btn_update.setOnClickListener {
            mTitle.clear()
            for (i in 0 until 5) {
                mTitle.add("2title ${i + 1}")
            }
            mAdapter.notifyDataSetChanged(mTitle)
        }

        btn_re.setOnClickListener {
            mTitle.clear()
            for (i in 0 until 10) {
                mTitle.add("title ${i + 1}")
            }
            mAdapter.notifyDataSetChanged(mTitle)
        }

        btn_remove.setOnClickListener {
            if (mTitle.isNullOrEmpty()) return@setOnClickListener
            mTitle.removeAt(0)
            mAdapter.notifyDataSetChanged()
        }

    }
}