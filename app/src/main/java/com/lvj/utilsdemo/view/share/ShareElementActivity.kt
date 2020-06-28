package com.lvj.utilsdemo.view.share

import android.graphics.Rect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.R
import kotlinx.android.synthetic.main.activity_share_element.*

class ShareElementActivity : AppCompatActivity() {

    companion object {
        val TAG = "ShareElementActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_element)


        tv_share.setOnClickListener {
            val rect = Rect()
            tv_share.getGlobalVisibleRect(rect)
            val fragment =
                ShareElementFragment.newInstance(rect)
            supportFragmentManager.beginTransaction()
                .addSharedElement(tv_share, "share")
                .addToBackStack(null)
                .replace(android.R.id.content, fragment)
                .commit()

        }
    }
}