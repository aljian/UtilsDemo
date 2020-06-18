package com.lvj.utilsdemo.view.anim

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.view.point.CustomPointViewActivity
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)


        btn_xml.setOnClickListener {
            startActivity(Intent(this, AnimationXmlActivity::class.java))
        }

        btn_value.setOnClickListener {
            startActivity(Intent(this, AnimationValueAnimatorActivity::class.java))
        }

        btn_point.setOnClickListener {
            startActivity(Intent(this, CustomPointViewActivity::class.java))
        }

        btn_obj.setOnClickListener {
            startActivity(Intent(this,AnimationObjectActivity::class.java))
        }
    }

}