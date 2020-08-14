package com.lvj.utilsdemo.view.behavior

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.dp2px
import com.lvj.utilsdemo.util.getScreenHeightPx
import kotlinx.android.synthetic.main.activity_drag.*
import kotlin.math.abs


class DragViewActivity : AppCompatActivity() {

    private var defaultHeight = 0
    private var minHeight = 0
    private var maxHeight = 0
    private var downY = 0f

    private val mData = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag)

        defaultHeight = dp2px(256).toInt()
        maxHeight = getScreenHeightPx() * 3 / 4

        val ss = StringBuffer()
        for (i in 0..201) {
            ss.append("这是主页面")
        }
        tv_home.text = ss.toString()

        for (i in 0..3) {
            mData.add("pos = $i")
        }
        rv_drag.layoutManager = LinearLayoutManager(this)

        rv_drag.adapter = MyAdapter()

        neScrollView.post {
            val params = neScrollView.layoutParams
            val parentV = neScrollView.parent as View
            params.height = parentV.height - defaultHeight
            neScrollView.layoutParams = params
        }

        initListener()
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
        iv_drag.setOnTouchListener { v: View, event: MotionEvent ->
            v.parent.requestDisallowInterceptTouchEvent(true)
            when (event.action) {
                MotionEvent.ACTION_DOWN -> downY = event.y
                MotionEvent.ACTION_MOVE -> {
                    val moveY = event.y
                    if (abs(moveY - downY) > ViewConfiguration.get(this).scaledTouchSlop) {
                        changeLayoutHeight(moveY - downY)
                    }
                }
                MotionEvent.ACTION_UP -> {
                }
            }
            true
        }
    }


    private var dragState = 0

    private fun changeLayoutHeight(distance: Float) {
        var curHeight = (rv_drag.height - distance).toInt()
        var curState = 0
        when {
            curHeight >= maxHeight -> {
                curHeight = maxHeight
                curState = 1
            }
            curHeight <= minHeight -> {
                curHeight = minHeight
                curState = 2
            }
            else -> {
                curState = 0
            }
        }
        if (dragState != curState) {
            dragState = curState
            when (dragState) {
                1 -> {
                    iv_drag.setImageResource(android.R.color.holo_blue_light)
                }
                2 -> {
                    iv_drag.setImageResource(android.R.color.holo_green_light)
                }
                else -> {
                    iv_drag.setImageResource(android.R.color.holo_red_light)
                }
            }
        }
        val layoutParams = rv_drag.layoutParams
        layoutParams.height = curHeight
        rv_drag.layoutParams = layoutParams

        val params = neScrollView.layoutParams
        val parentV = neScrollView.parent as View
        params.height = parentV.height - curHeight
        neScrollView.layoutParams = params

    }


    inner class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(this@DragViewActivity).inflate(R.layout.item_text, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.item_tv.text = mData[position]
            holder.itemView.setOnClickListener {
                Toast.makeText(this@DragViewActivity, mData[position], Toast.LENGTH_SHORT).show()
            }
        }

        override fun getItemCount(): Int {
            return mData.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val item_tv: TextView = itemView.findViewById(R.id.item_tv)

        }
    }
}
