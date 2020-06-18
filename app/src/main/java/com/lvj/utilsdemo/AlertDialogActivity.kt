package com.lvj.utilsdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.util.toGsonString
import com.lvj.utilsdemo.util.toast
import kotlinx.android.synthetic.main.activity_alertdialog.*

class AlertDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alertdialog)


        btn_simple.setOnClickListener {
            showSimpleDialog()
        }

        btn_list.setOnClickListener {
            showListDialog()
        }

        btn_check.setOnClickListener {
            showCheckDialog()
        }

        btn_checks.setOnClickListener {
            showChecskDialog()
        }
    }

    private fun showChecskDialog() {
        AlertDialog.Builder(this)
            .setTitle("多选dialog")
            .setMultiChoiceItems(items, checkedItems) { dialog, which, isChecked ->
                if (isChecked) toast("选择了item$which") else toast("取消选择item$which")
            }
            .setPositiveButton("确定") { dialog, which ->
                toast("确定 checkedItems = ${checkedItems.toGsonString()}")
                Log.i("tagtag", "checkedItems = ${checkedItems.toGsonString()}")
            }
            .setNegativeButton("取消", null)
            .create()
            .show()
    }

    private var singleCheckPos = 0

    private fun showCheckDialog() {
        AlertDialog.Builder(this)
            .setTitle("单选dialog")
            .setSingleChoiceItems(items, singleCheckPos) { dialog, which ->
                singleCheckPos = which
            }
            .setPositiveButton("确定") { dialog, which ->
                toast("确定 check $singleCheckPos")
                Log.i("tagtag", "singleCheckPos = $singleCheckPos")
            }
            .setNegativeButton("取消", null)
            .create()
            .show()
    }

    private val items = arrayOf("item 0 ", "item 1 ", "item 2 ", "item 3 ", "item 4 ")
    val checkedItems = booleanArrayOf(true, false, false, false, false)


    private fun showListDialog() {
        AlertDialog.Builder(this)
            .setTitle("列表dialog")
            .setItems(items) { dialog, which -> toast("点击了条目$which") }
            .create()
            .show()
    }

    private fun showSimpleDialog() {
        AlertDialog.Builder(this)
            .setTitle("标题")
            .setIcon(R.mipmap.ic_launcher)
            .setMessage("这是内容")
            .setPositiveButton("确定") { dialog, which -> toast("确定") }
            .setNegativeButton("取消", null)
            .create()
            .show()
    }

}
