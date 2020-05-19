package com.lvj.utilsdemo.constraint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.R
import kotlinx.android.synthetic.main.activity_constraint.*

/**
 * ConstraintLayout 一些用法
 */
class ConstraintLayoutActivity : AppCompatActivity() {

    private var changePalceholder = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)


        changePlaceholder.setOnClickListener {
            if (changePalceholder) {
                place2.setContentId(-1)
                place1.setContentId(tv_palce.id)
                changePalceholder = false
            } else {
                place1.setContentId(-1)
                place2.setContentId(tv_palce.id)
                changePalceholder = true
            }

        }

    }

}
