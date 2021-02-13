package com.lvj.utilsdemo.rvcard

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CardLayoutManager(mContext: Context) : RecyclerView.LayoutManager() {

    companion object {
        //一次最多显示4个Item
        const val MAX_SHOW_COUNT: Int = 4

        //每级视差 scale 0.05F Y相差15dp左右
        const val SCALE_GAP: Float = 0.05f
    }

    val TRANS_Y_GAP = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, mContext.resources.displayMetrics)

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        detachAndScrapAttachedViews(recycler)
        val itemCount = itemCount
        // 在这里，我们默认配置 MAX_SHOW_COUNT = 3。即在屏幕上显示的卡片数为3
        // 当数据源个数大于最大显示数时
        if (itemCount > MAX_SHOW_COUNT) {
            // 把数据源倒着循环，这样，第0个数据就在屏幕最上面了
            for (position in MAX_SHOW_COUNT downTo 0) {
                val view: View = recycler.getViewForPosition(position)
                // 将 Item View 加入到 RecyclerView 中
                addView(view)
                // 测量 Item View
                measureChildWithMargins(view, 0, 0)
                // getDecoratedMeasuredWidth(view) 可以得到 Item View 的宽度
                // 所以 widthSpace 就是除了 Item View 剩余的值
                val widthSpace = width - getDecoratedMeasuredWidth(view)
                // 同理
                val heightSpace = height - getDecoratedMeasuredHeight(view)
                // 将 Item View 放入 RecyclerView 中布局
                // 在这里默认布局是放在 RecyclerView 中心
                layoutDecoratedWithMargins(
                    view, widthSpace / 2, heightSpace / 2,
                    widthSpace / 2 + getDecoratedMeasuredWidth(view),
                    heightSpace / 2 + getDecoratedMeasuredHeight(view)
                )
                // 其实屏幕上有四张卡片，但是我们把第三张和第四张卡片重叠在一起，这样看上去就只有三张
                // 第四张卡片主要是为了保持动画的连贯性
                if (position == MAX_SHOW_COUNT) {
                    // 按照一定的规则缩放，并且偏移Y轴。
                    // SCALE_GAP 默认为0.1f，TRANS_Y_GAP 默认为14
                    view.scaleX = 1 - (position - 1) * SCALE_GAP
                    view.scaleY = 1 - (position - 1) * SCALE_GAP
                    view.translationY = (position - 1) * view.measuredHeight / TRANS_Y_GAP
                } else if (position > 0) {
                    view.scaleX = 1 - position * SCALE_GAP
                    view.scaleY = 1 - position * SCALE_GAP
                    view.translationY = position * view.measuredHeight / TRANS_Y_GAP
                }
            }
        } else {
            // 当数据源个数小于或等于最大显示数时，和上面的代码差不多
            for (position in itemCount - 1 downTo 0) {
                val view: View = recycler.getViewForPosition(position)
                addView(view)
                measureChildWithMargins(view, 0, 0)
                val widthSpace = width - getDecoratedMeasuredWidth(view)
                val heightSpace = height - getDecoratedMeasuredHeight(view)
                layoutDecoratedWithMargins(
                    view, widthSpace / 2, heightSpace / 2,
                    widthSpace / 2 + getDecoratedMeasuredWidth(view),
                    heightSpace / 2 + getDecoratedMeasuredHeight(view)
                )
                if (position > 0) {
                    view.scaleX = 1 - position * SCALE_GAP
                    view.scaleY = 1 - position * SCALE_GAP
                    view.translationY = position * view.measuredHeight / TRANS_Y_GAP
                }
            }
        }

    }


}