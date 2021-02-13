package com.lvj.utilsdemo.rvcard

import android.content.Context
import android.graphics.Canvas
import android.util.TypedValue
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.lvj.utilsdemo.rvcard.OverLayCardLayoutManager.Companion.MAX_SHOW_COUNT
import com.lvj.utilsdemo.rvcard.OverLayCardLayoutManager.Companion.SCALE_GAP
import kotlin.math.sqrt

class ItemTouchCallBack(mContext: Context, var onDeleteListener: ((position: Int) -> Unit)) : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.DOWN or ItemTouchHelper.UP or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {

    val TRANS_Y_GAP: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, mContext.resources.displayMetrics).toInt()

    /**
     * 滑动删除阈值
     */
    private fun getThreshold(rv: RecyclerView, viewHolder: RecyclerView.ViewHolder): Float {
        return rv.width * 0.5f
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onDeleteListener.invoke(viewHolder.layoutPosition)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

//        val swipValue = sqrt(dX * dX + dY * dY)
//        var fraction = swipValue / getThreshold(recyclerView, viewHolder)
//        //边界修正
//        if (fraction > 1) fraction = 1f
//        //对每个childView进行位移缩放
//        val childCount = recyclerView.childCount
//        for (i in 0 until childCount) {
//            val child = recyclerView.getChildAt(i)
//            val level = childCount - i - 1
//            if (level > 0) {
//                child.scaleX = 1 - SCALE_GAP * level + fraction + SCALE_GAP
//                if (level < MAX_SHOW_COUNT - 1) {
//                    child.scaleY = 1 - SCALE_GAP * level + fraction + SCALE_GAP
//                    child.translationY = TRANS_Y_GAP * level - fraction * TRANS_Y_GAP
//                }
//
//
//            }
//        }


    }
}