package com.lvj.utilsdemo.work

import android.content.Context
import androidx.work.*
import com.lvj.utilsdemo.util.logi
import kotlin.random.Random

/**
 * [Worker] [WorkManager]
 *
 */
class MyWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val s = Random.nextInt(3)
        logi("myWork doWork s = $s")

        val map = inputData.keyValueMap
        logi("myWork doWork map = $map")

        return when (s % 3) {
            0 -> {
                logi("myWork doWork success")
                Result.success()
            }
            1 -> {
                logi("myWork doWork failure")
                Result.failure()
            }
            else -> {
                logi("myWork doWork retry")
                Result.retry()
            }
        }
    }
}

class MyWork2(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val s = Random.nextInt(3)

        logi("myWork doWork s = $s")
        logi("myWork doWork map = ${inputData.keyValueMap}")

        setProgress(Data.Builder().let {
            it.putInt("progress", 0)
            it.build()
        })
        Thread.sleep(1000L)
        setProgress(Data.Builder().let {
            it.putInt("progress", 50)
            it.build()
        })
        Thread.sleep(1000L)
        setProgress(Data.Builder().let {
            it.putInt("progress", 100)
            it.build()
        })
        return when (s % 3) {
            0 -> {
                logi("myWork doWork success")
                Result.success(inputData)
            }
            1 -> {
                logi("myWork doWork failure")
                Result.failure()
            }
            else -> {
                logi("myWork doWork retry")
                Result.retry()
            }
        }
    }


}