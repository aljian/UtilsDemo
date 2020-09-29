package com.lvj.utilsdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lvj.ruleview.RulerView
import kotlinx.android.synthetic.main.activity_ruler.*

class RulerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ruler)

        rulerView.setOnNumSelectListener(object : RulerView.OnNumSelectListener {

            override fun onNumSelect(selectedNum: Int) {
                tv_ruler.text = "$selectedNum cm"
                tv_ruler.setTextColor(rulerView.indicatorColor)
            }

        })
    }
}