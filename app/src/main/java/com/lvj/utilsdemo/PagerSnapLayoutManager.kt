package com.lvj.utilsdemo

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.lvj.utilsdemo.util.logi


class PagerSnapLayoutManager : LinearLayoutManager, RecyclerView.OnChildAttachStateChangeListener {

    private var mPagerSnapHelper: PagerSnapHelper = PagerSnapHelper()

    constructor(context: Context) : super(context)

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)

    override fun onAttachedToWindow(view: RecyclerView?) {
        view?.addOnChildAttachStateChangeListener(this)
        mPagerSnapHelper.attachToRecyclerView(view)
        super.onAttachedToWindow(view)
    }

    override fun onChildViewAttachedToWindow(view: View) {
        //通过这个方法确定当前滑动到哪个页面 在快速来回重复滑动的时候不准确
//        mSelectedListener?.invoke(getPosition(view))
    }

    override fun onChildViewDetachedFromWindow(view: View) {
        mReleaseListener?.invoke(getPosition(view))
    }

    override fun onScrollStateChanged(state: Int) {
        when (state) {
            RecyclerView.SCROLL_STATE_IDLE -> {
                val view = mPagerSnapHelper.findSnapView(this)
                view?.let {
                    mSelectedListener?.invoke(getPosition(it))
                }
            }
        }
        super.onScrollStateChanged(state)
    }


    private var mSelectedListener: ((position: Int) -> Unit?)? = null
    private var mReleaseListener: ((position: Int) -> Unit?)? = null

    fun setListener(selectedListener: (position: Int) -> Unit = {}, releaseListener: (position: Int) -> Unit = {}) {
        this.mSelectedListener = selectedListener
        this.mReleaseListener = releaseListener
    }


}