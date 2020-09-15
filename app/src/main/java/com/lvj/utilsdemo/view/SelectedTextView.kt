package com.lvj.utilsdemo.view

import android.content.Context
import android.util.AttributeSet
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatTextView
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.logi

class SelectedTextView : AppCompatTextView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setTextIsSelectable(true)
        customSelectionActionModeCallback = SelectedCallBack()
    }

    private inner class SelectedCallBack : ActionMode.Callback {

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            //返回false则不会显示弹窗
            return true
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.select_1 -> {
                    actionListener?.invoke(0)
                    mode?.finish()
                }
                R.id.select_2 -> {
                    actionListener?.invoke(1)
                }
                else -> {

                }
            }
            return true//返回true则系统item将无效，只有自定义item有响应
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            val menuInflater = mode?.menuInflater
            menu?.clear()
            menuInflater?.inflate(R.menu.selection_action_menu, menu)
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            logi("onDestroyActionMode")
        }

    }

    private var actionListener: ((Int) -> Unit)? = null

    fun setActionItemClicked(listener: (position: Int) -> Unit) {
        this.actionListener = listener
    }


}