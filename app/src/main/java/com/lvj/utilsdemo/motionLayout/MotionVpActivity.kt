package com.lvj.utilsdemo.motionLayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.lvj.utilsdemo.R
import kotlinx.android.synthetic.main.activity_motion_viewpager.*

class MotionVpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_viewpager)

        vp2_vp.adapter = MyAdapter(this)
        TabLayoutMediator(tab_vp, vp2_vp) { tab, position ->
            tab.text = "tab $position"
        }.attach()

        vp2_vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                motion_vp.progress = (position + positionOffset) / (3 - 1)
            }

            override fun onPageSelected(position: Int) {

            }
        })

    }


    class MyAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount() = 3

        override fun createFragment(position: Int): Fragment {
            val fragment = MotionFragment()
            val bundle = Bundle()
            bundle.putString("text", "fragment $position")
            fragment.arguments = bundle
            return fragment
        }
    }

}

