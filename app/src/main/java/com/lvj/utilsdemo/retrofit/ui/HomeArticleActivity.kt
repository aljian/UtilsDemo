package com.lvj.utilsdemo.retrofit.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.retrofit.api.ArticleListEntity
import com.lvj.utilsdemo.retrofit.ui.HomeArticleActivity.MyAdapter.ViewHolder
import kotlinx.android.synthetic.main.activity_home_acticle.*

class HomeArticleActivity : AppCompatActivity() {

    private val mViewModel by viewModels<HomeArticleViewModel>()

    private val mData = mutableListOf<ArticleListEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_acticle)

        mViewModel.getHomeArticle(1)

        rv_home_articler.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = MyAdapter(this, mData)


        }

        initListener()
    }

    private fun initListener() {

        mViewModel.mResult.observe(this, Observer {
            mData.clear()
            mData.addAll(it)
            rv_home_articler.adapter?.notifyDataSetChanged()
        })
    }


    private inner class MyAdapter(var mContext: Context, var mList: MutableList<ArticleListEntity>) :
        RecyclerView.Adapter<ViewHolder>() {


        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var tv_text: TextView = view.findViewById(R.id.item_tv)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_text, parent, false))
        }

        override fun getItemCount() = mList.size


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tv_text.text = mList[position].title
        }

    }


}