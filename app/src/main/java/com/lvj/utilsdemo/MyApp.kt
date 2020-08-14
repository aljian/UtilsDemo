package com.lvj.utilsdemo

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(ProcessLifecycleObserver())
        registerActivityCallbacks()
    }


    inner class ProcessLifecycleObserver : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun enterAppListener() {
            Log.e("ProcessLifecycle", "ON_CREATE")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun exitAppListener() {
            Log.e("ProcessLifecycle", "ON_STOP")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onAppStart() {
            Log.e("ProcessLifecycle", "ON_START")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onAppResume() {
            Log.e("ProcessLifecycle", "ON_RESUME")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onAppPause() {
            Log.e("ProcessLifecycle", "ON_PAUSE")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onAppDestroy() {
            Log.e("ProcessLifecycle", "ON_DESTROY")
        }
    }

    private fun registerActivityCallbacks() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

            }

            override fun onActivityResumed(activity: Activity) {

            }


        })
    }

}