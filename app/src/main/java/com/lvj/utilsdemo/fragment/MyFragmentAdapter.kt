package com.lvj.utilsdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyFragmentAdapter(private var mTitle: MutableList<String>, activity: FragmentActivity) :
    FragmentStateAdapter(activity) {

    private val ids = mutableListOf<Long>()

    private val contentIds = mutableListOf<Long>()

    init {
        ids.clear()
        mTitle.forEach {
            ids.add(it.hashCode().toLong())
        }
    }

    fun notifyDataSetChanged(mTitle: MutableList<String>) {
        this.mTitle = mTitle
        ids.clear()
        mTitle.forEach {
            ids.add(it.hashCode().toLong())
        }
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = mTitle.size

    override fun createFragment(position: Int): Fragment {
        contentIds.add(position, ids[position])
        val fragment = TabDemoFragment()
        val bundle = Bundle()
        bundle.putString("title", mTitle[position])
        fragment.arguments = bundle
        return fragment
    }

    override fun getItemId(position: Int): Long {
        return ids[position]
    }

    override fun containsItem(itemId: Long): Boolean {
        return contentIds.contains(itemId)
    }


}