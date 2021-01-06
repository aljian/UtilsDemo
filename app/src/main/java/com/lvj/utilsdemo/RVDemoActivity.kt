package com.lvj.utilsdemo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lvj.utilsdemo.RVDemoActivity.MyAdapter.ViewHolder
import com.lvj.utilsdemo.util.logi
import kotlinx.android.synthetic.main.activity_rv_demo.*

class RVDemoActivity : AppCompatActivity() {

    private lateinit var mAdapter: MyAdapter
    private var layoutManager: PagerSnapLayoutManager? = null

    private var mCurrentPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_demo)

        val mData = mutableListOf<String>()
        for (i in 0 until 50) {
            mData.add("当前 position = $i")
        }
        layoutManager = PagerSnapLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        layoutManager!!.setListener({
            logi("onPageSelected position = $it")
            mCurrentPos = it
        }, {
            logi("onPageRelease position = $it")
        })

        rv_demo.layoutManager = layoutManager
        mAdapter = MyAdapter(this, mData)
        rv_demo.adapter = mAdapter
        val animator = DefaultItemAnimator()
        animator.changeDuration = 2000
        rv_demo.itemAnimator = animator

        mCurrentPos = 0
//        mHandler.sendEmptyMessageDelayed(0, 1000)
        handler.sendEmptyMessageDelayed(0, 1000)
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {

            this.sendEmptyMessageDelayed(0, 1000)
            logi("mCurrentPos = $mCurrentPos")
        }
    }

    private val handler: Handler = Handler {
        timer()
        return@Handler true
    }

    private fun timer() {
        logi("mCurrentPos = $mCurrentPos")
        handler.sendEmptyMessageDelayed(0, 1000)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        mHandler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }


    inner class MyAdapter(private val mContext: Context, private val mData: List<String>) : RecyclerView.Adapter<ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val item_tv_tx = itemView.findViewById<TextView>(R.id.item_tv_tx)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rv_demo, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.item_tv_tx.text = mData[position]

            holder.item_tv_tx.setOnClickListener {
                logi("next")
                rv_demo.smoothScrollToPosition(position + 1)
//                showPop(it)
            }
        }

        override fun getItemCount() = if (mData.isNullOrEmpty()) 0 else mData.size

    }


    private fun showPop(v: View) {
        val menu = PopupMenu(this, v, Gravity.END)
        menu.menuInflater.inflate(R.menu.pop_menu, menu.menu)
        menu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.pop_item_1 -> {
                    logi("11111")
                }
                R.id.pop_item_2 -> {
                    logi("22222")
                }
            }
            true
        }
        menu.show()
    }

}