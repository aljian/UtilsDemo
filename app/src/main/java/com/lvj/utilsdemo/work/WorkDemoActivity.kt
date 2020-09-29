package com.lvj.utilsdemo.work

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.lvj.utilsdemo.R
import com.lvj.utilsdemo.util.logi
import kotlinx.android.synthetic.main.activity_work_demo.*
import java.util.concurrent.TimeUnit

class WorkDemoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_demo)

        tv_doWork.setOnClickListener {
            startWork()
        }

    }

    private val workTag = "WORK_TAG"

    private fun startWork() {

        val map = mutableMapOf<String, Any>()
        map["name"] = "张三"
        map["age"] = "12"
        /*  执行一次 */
        val request = OneTimeWorkRequestBuilder<MyWork2>()
            .addTag(workTag)
            .setInputData(Data.Builder().putAll(map).build())
            .build()

        /*  执行多次 注意 这个任务被google设置了 最低15分钟 */
//        val request = PeriodicWorkRequestBuilder<MyWork>(
//            5, TimeUnit.SECONDS,
//        )
//            .setInitialDelay(3, TimeUnit.SECONDS)//
//            .addTag(workTag)
//            .build()

        WorkManager.getInstance(this).enqueue(request)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id).observe(this) {

            when (it.state) {
                WorkInfo.State.ENQUEUED -> {
                    logi("observe ENQUEUED")
                }
                WorkInfo.State.RUNNING -> {
                    logi("observe RUNNING progress = ${it.progress.getInt("progress", -1)}")
                }
                WorkInfo.State.SUCCEEDED -> {
                    logi("observe SUCCEEDED progress = ${it.progress.getInt("progress", -1)}")
                    logi("observe SUCCEEDED map = ${it.outputData.keyValueMap}")
                }
                WorkInfo.State.FAILED -> {
                    logi("observe FAILED")
                }
                WorkInfo.State.CANCELLED -> {
                    logi("observe CANCELLED")
                }
                else -> {
                    logi("observe else")
                }
            }
        }

    }

    override fun onDestroy() {
        WorkManager.getInstance(this).cancelAllWorkByTag(workTag)
        super.onDestroy()
    }

}