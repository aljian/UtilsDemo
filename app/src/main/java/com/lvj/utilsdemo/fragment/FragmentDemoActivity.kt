package com.lvj.utilsdemo.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.logi
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
            mTitle.removeAt(0)
            mAdapter.notifyDataSetChanged()
        }

        /** using [DiffUtil] */
//        val idsOld = items.createIdSnapshot()
//        performChanges()
//        val idsNew = items.createIdSnapshot()
//        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
//            override fun getOldListSize(): Int = idsOld.size
//            override fun getNewListSize(): Int = idsNew.size
//
//            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
//                idsOld[oldItemPosition] == idsNew[newItemPosition]
//
//            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
//                areItemsTheSame(oldItemPosition, newItemPosition)
//        }, true).dispatchUpdatesTo(viewPager.adapter!!)

    }
}