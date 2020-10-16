package com.lvj.utilsdemo

import android.content.Context
import android.util.TypedValue
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class OverLayCardLayoutManager2(mContext: Context) : RecyclerView.LayoutManager() {

    private val TRANS_Y_GAP: Int

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: Recycler, state: RecyclerView.State) {
        detachAndScrapAttachedViews(recycler)
        val itemCount = itemCount
        if (itemCount >= MAX_SHOW_COUNT) {
            //从可见的最底层View开始layout，依次层叠上去
            for (position in itemCount - MAX_SHOW_COUNT until itemCount) {
                val view = recycler.getViewForPosition(position)
                addView(view)
                measureChildWithMargins(view, 0, 0)
                val widthSpace = width - getDecoratedMeasuredWidth(view)
                val heightSpace = height - getDecoratedMeasuredHeight(view)
                //我们在布局时，将childView居中处理，这里也可以改为只水平居中
                layoutDecoratedWithMargins(
                    view, widthSpace / 2, heightSpace / 2,
                    widthSpace / 2 + getDecoratedMeasuredWidth(view),
                    heightSpace / 2 + getDecoratedMeasuredHeight(view)
                )
                /**
                 * TopView的Scale 为1，translationY 0
                 * 每一级Scale相差0.05f，translationY相差7dp左右
                 *
                 * 观察人人影视的UI，拖动时，topView被拖动，Scale不变，一直为1.
                 * top-1View 的Scale慢慢变化至1，translation也慢慢恢复0
                 * top-2View的Scale慢慢变化至 top-1View的Scale，translation 也慢慢变化只top-1View的translation
                 * top-3View的Scale要变化，translation岿然不动
                 */

                //第几层,举例子，count =7， 最后一个TopView（6）是第0层，
                val level = itemCount - position - 1
                //除了顶层不需要缩小和位移
                if (level > 0) {
                    //每一层都需要X方向的缩小
                    view.scaleX = 1 - SCALE_GAP * level
                    //前N层，依次向下位移和Y方向的缩小
                    if (level < MAX_SHOW_COUNT - 1) {
                        view.translationY = TRANS_Y_GAP * level.toFloat()
                        view.scaleY = 1 - SCALE_GAP * level
                    } else { //第N层在 向下位移和Y方向的缩小的成都与 N-1层保持一致
                        view.translationY = TRANS_Y_GAP * (level - 1).toFloat()
                        view.scaleY = 1 - SCALE_GAP * (level - 1)
                    }
                }
            }
        }
    }

    companion object {
        private const val MAX_SHOW_COUNT = 4
        private const val SCALE_GAP = 0.05f
    }

    init {
        TRANS_Y_GAP = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, mContext.resources.displayMetrics).toInt()
    }
}