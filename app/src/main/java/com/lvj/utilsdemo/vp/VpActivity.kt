package com.lvj.utilsdemo.vp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.view.BottomNavigationView
import kotlinx.android.synthetic.main.activity_layout_vp.*

class VpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_vp)

        //禁止左右滑动切换
        vp2.isUserInputEnabled = false

        bnv.setOnItemSelectedListener { position ->
            vp2.setCurrentItem(position, false)
        }

        vp2.adapter = object : FragmentStateAdapter(this) {

            override fun getItemCount(): Int {
                return 4
            }

            override fun createFragment(position: Int): Fragment {
                return VpFragment.newInstance("position = $position")
            }

        }

        val mTitle = mutableListOf("1111", "2222", "3333", "4444")
        bnv.setTitles(mTitle)
    }
}