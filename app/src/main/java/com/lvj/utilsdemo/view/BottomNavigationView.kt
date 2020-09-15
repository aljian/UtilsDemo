package com.lvj.utilsdemo.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.lvj.utilsdemo.R


class BottomNavigationView : FrameLayout {

    private lateinit var ll_1: LinearLayout
    private lateinit var tv_1: TextView
    private lateinit var iv_1: ImageView

    private lateinit var ll_2: LinearLayout
    private lateinit var tv_2: TextView
    private lateinit var iv_2: ImageView

    private lateinit var ll_3: LinearLayout
    private lateinit var tv_3: TextView
    private lateinit var iv_3: ImageView

    private lateinit var ll_4: LinearLayout
    private lateinit var tv_4: TextView
    private lateinit var iv_4: ImageView

    private val defaultTitles = mutableListOf("主页", "第二页", "第三页", "第四页")
    private val defaultIconDrawable = mutableListOf(
        R.drawable.draw_home, R.drawable.draw_second,
        R.drawable.draw_third, R.drawable.draw_my
    )

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.custom_bottom_navigation, this, true)
        findAllViews()
        initListener()
        setSelectedItem(0)
    }

    private fun findAllViews() {
        ll_1 = findViewById(R.id.ll_1)
        tv_1 = findViewById(R.id.tv_1)
        iv_1 = findViewById(R.id.iv_1)

        ll_2 = findViewById(R.id.ll_2)
        tv_2 = findViewById(R.id.tv_2)
        iv_2 = findViewById(R.id.iv_2)

        ll_3 = findViewById(R.id.ll_3)
        tv_3 = findViewById(R.id.tv_3)
        iv_3 = findViewById(R.id.iv_3)

        ll_4 = findViewById(R.id.ll_4)
        tv_4 = findViewById(R.id.tv_4)
        iv_4 = findViewById(R.id.iv_4)

        resetData()
    }

    fun resetData() {
        tv_1.text = defaultTitles[0]
        iv_1.setImageResource(defaultIconDrawable[0])
        tv_2.text = defaultTitles[1]
        iv_2.setImageResource(defaultIconDrawable[1])
        tv_3.text = defaultTitles[2]
        iv_3.setImageResource(defaultIconDrawable[2])
        tv_4.text = defaultTitles[3]
        iv_4.setImageResource(defaultIconDrawable[3])
    }

    private fun initListener() {
        ll_1.setOnClickListener {
            if (ll_1.isSelected) {
                return@setOnClickListener
            }
            selectedItem(0)
            listener?.invoke(0)
        }

        ll_2.setOnClickListener {
            if (ll_2.isSelected) {
                return@setOnClickListener
            }
            clearAllState()
            selectedItem(1)
            listener?.invoke(1)
        }

        ll_3.setOnClickListener {
            if (ll_3.isSelected) {
                return@setOnClickListener
            }
            selectedItem(2)
            listener?.invoke(2)
        }

        ll_4.setOnClickListener {
            if (ll_4.isSelected) {
                return@setOnClickListener
            }
            selectedItem(3)
            listener?.invoke(3)
        }
    }

    private fun selectedItem(position: Int) {
        when (position) {
            0 -> {
                clearAllState()
                ll_1.isSelected = true
                tv_1.isSelected = true
                iv_1.isSelected = true
            }
            1 -> {
                ll_2.isSelected = true
                tv_2.isSelected = true
                iv_2.isSelected = true
            }
            2 -> {
                clearAllState()
                ll_3.isSelected = true
                tv_3.isSelected = true
                iv_3.isSelected = true
            }
            3 -> {
                clearAllState()
                ll_4.isSelected = true
                tv_4.isSelected = true
                iv_4.isSelected = true
            }
        }
    }

    private fun clearAllState() {
        if (ll_1.isSelected) {
            ll_1.isSelected = false
            tv_1.isSelected = false
            iv_1.isSelected = false
        }
        if (ll_2.isSelected) {
            ll_2.isSelected = false
            tv_2.isSelected = false
            iv_2.isSelected = false
        }
        if (ll_3.isSelected) {
            ll_3.isSelected = false
            tv_3.isSelected = false
            iv_3.isSelected = false
        }
        if (ll_4.isSelected) {
            ll_4.isSelected = false
            tv_4.isSelected = false
            iv_4.isSelected = false
        }
    }

    fun setSelectedItem(position: Int) {
        selectedItem(position)
    }

    fun setDatas(mTitles: MutableList<String>, mIcon: MutableList<Int>) {
        checkData(mTitles)
        checkData(mIcon)
        defaultTitles.clear()
        defaultTitles.addAll(mTitles)
        defaultIconDrawable.clear()
        defaultIconDrawable.addAll(mIcon)
        resetData()
    }

    fun setTitles(mTitles: MutableList<String>) {
        checkData(mTitles)
        defaultTitles.clear()
        defaultTitles.addAll(mTitles)
        resetData()
    }

    fun setIconDrawables(mIcon: MutableList<Int>) {
        checkData(mIcon)
        defaultIconDrawable.clear()
        defaultIconDrawable.addAll(mIcon)
        resetData()
    }

    private fun <T> checkData(mData: MutableList<T>) {
        if (mData.isNullOrEmpty()) {
            throw NullPointerException("导航栏文字、图片不能为空")
        }
        if (mData.size != 4) {
            throw IllegalAccessException("导航栏文字、图片集合大小必须为4")
        }
    }


    /***************************** kotlin lambda 接口回调示例 *************************************************/

    var listener: ((position: Int) -> Unit)? = null

    fun setOnItemSelectedListener(listener: ((position: Int) -> Unit)) {
        this.listener = listener
    }


    var mListener: ((position: Int) -> Int)? = null

    fun setOnSelectedListener(listener: ((position: Int) -> Int)) {
        this.mListener = listener
    }





}