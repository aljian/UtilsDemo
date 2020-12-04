package com.lvj.utilsdemo.rvcard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.lvj.utilsdemo.GlideApp
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.logi
import kotlinx.android.synthetic.main.activity_rv_card.*

class RvCardActivity : AppCompatActivity() {

    private val mData = mutableListOf<String>()
    private var mAdapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_card)

        mData.add("http://www.xinanw.cn/UpLoadFiles/95207035701.jpg")
        mData.add("http://www.xinanw.cn/UpLoadFiles/76121604820.jpg")
        mData.add("http://www.xinanw.cn/UpLoadFiles/2687963200.jpg")
        mData.add("http://www.xinanw.cn/UpLoadFiles/57211021639.jpg")
        mData.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=366763321,1800567094&fm=26&gp=0.jpg")
        mData.add("http://www.xinanw.cn/UpLoadFiles/95207035701.jpg")
        mData.add("http://www.xinanw.cn/UpLoadFiles/76121604820.jpg")
        mData.add("http://www.xinanw.cn/UpLoadFiles/2687963200.jpg")
        mData.add("http://www.xinanw.cn/UpLoadFiles/57211021639.jpg")


        rv_card.run {
            layoutManager = CardLayoutManager(this@RvCardActivity)
            mAdapter = MyAdapter(this@RvCardActivity)
            adapter = mAdapter
        }


        val callBack = ItemTouchCallBack(this) {
            logi("回调 position = $it")
//            val removeAt = mData.removeAt(it)
            mData.removeAt(it)
//            mData.add(0, removeAt)
            mAdapter?.notifyDataSetChanged()
        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(rv_card)

    }


    inner class MyAdapter(private val mContext: Context) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val item_iv_card: ImageView = itemView.findViewById(R.id.item_iv_card)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
            return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rv_card, parent, false))
        }

        override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
            GlideApp.with(mContext).load(mData[position]).into(holder.item_iv_card)
        }

        override fun getItemCount(): Int {
            return mData.size
        }
    }


//    val callback = object : ItemTouchHelper.SimpleCallback(
//        0,
//        ItemTouchHelper.DOWN or ItemTouchHelper.UP or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//    ) {
//
//        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
//            return false
//        }
//
//        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//            val remove: String = mData.removeAt(viewHolder.layoutPosition)
//            mData.add(0, remove);
//            mAdapter?.notifyDataSetChanged()
//
//        }
//
//    }


}