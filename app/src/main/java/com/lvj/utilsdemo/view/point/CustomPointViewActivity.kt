package com.lvj.utilsdemo.view.point

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.R
import kotlinx.android.synthetic.main.activity_custom_point_view.*

class CustomPointViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_point_view)

        btn_start.setOnClickListener {
            pointView.doPointAnim()
        }
    }

}
