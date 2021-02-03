package com.lvj.utilsdemo.rv

import androidx.recyclerview.widget.RecyclerView

/**
 *
 */
class DemoLayoutManager : RecyclerView.LayoutManager() {


    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {


    }


}