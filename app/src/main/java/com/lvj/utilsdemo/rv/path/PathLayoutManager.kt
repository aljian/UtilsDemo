package com.lvj.utilsdemo.rv.path

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PathLayoutManager : RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)


    }

    override fun canScrollHorizontally(): Boolean {
        return super.canScrollHorizontally()
    }

    override fun canScrollVertically(): Boolean {
        return super.canScrollVertically()
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        return super.scrollHorizontallyBy(dx, recycler, state)
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        return super.scrollVerticallyBy(dy, recycler, state)
    }


}