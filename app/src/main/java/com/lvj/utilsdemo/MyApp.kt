package com.lvj.utilsdemo

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import java.util.*
import kotlin.properties.Delegates


class MyApp : Application() {

    companion object {
        var instance: MyApp by Delegates.notNull()

        val mActivities = Stack<Activity>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(ProcessLifecycleObserver())
        registerActivityCallbacks()
//        crashHandler()

    }

    private fun crashHandler() {
        Handler(mainLooper).post {
            while (true) {
                try {
                    Looper.loop()
                } catch (e: Exception) {
                    mActivities.lastElement().finish()
                    Toast.makeText(this, "抛出了异常", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
        }
    }


    class ProcessLifecycleObserver : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun enterAppListener() {
            Log.i("ProcessLifecycle", "ON_CREATE")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun exitAppListener() {
            Log.i("ProcessLifecycle", "ON_STOP")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onAppStart() {
            Log.i("ProcessLifecycle", "ON_START")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onAppResume() {
            Log.i("ProcessLifecycle", "ON_RESUME")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onAppPause() {
            Log.i("ProcessLifecycle", "ON_PAUSE")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onAppDestroy() {
            Log.i("ProcessLifecycle", "ON_DESTROY")
        }
    }

    private fun registerActivityCallbacks() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityDestroyed(activity: Activity) {
                mActivities.remove(activity)
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                mActivities.add(activity)
            }

            override fun onActivityResumed(activity: Activity) {

            }


        })
    }

}