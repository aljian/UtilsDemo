package com.lvj.utilsdemo.playview.utils

import android.content.Context
import android.hardware.SensorManager
import android.view.OrientationEventListener
import com.lvj.utilsdemo.playview.Orientation

class OrientationWatchDog(context: Context) {

    private val mContext: Context = context.applicationContext

    //系统的屏幕方向改变监听
    private var mLandOrientationListener: OrientationEventListener? = null

    //对外监听
    private var mOrientationListener: OnOrientationListener? = null

    //上次屏幕的方向
    private var mLastOrientation = Orientation.Port

    fun startWatch() {
        if (mLandOrientationListener == null) {
            mLandOrientationListener = object : OrientationEventListener(mContext, SensorManager.SENSOR_DELAY_NORMAL) {

                override fun onOrientationChanged(orientation: Int) {
                    if (orientation == -1) return
                    val isLand = (orientation in 81..99 || orientation in 261..279)
                    val isPort = (orientation < 10 || orientation > 350) || (orientation in 171..189)

                    if (isLand) {
                        if (mOrientationListener != null && orientation in 81..99) {
                            mOrientationListener!!.changedToLandReverseScape(
                                mLastOrientation == Orientation.Port
                                        || mLastOrientation == Orientation.Land_Forward
                            )
                            mLastOrientation = Orientation.Land_Reverse
                        } else if (mOrientationListener != null && orientation in 261..279) {
                            mOrientationListener!!.changedToLandForwardScape(
                                mLastOrientation == Orientation.Port
                                        || mLastOrientation == Orientation.Land_Reverse
                            )
                            mLastOrientation = Orientation.Land_Forward

                        }

                    } else if (isPort) {
                        mOrientationListener?.changedToPortrait(
                            mLastOrientation == Orientation.Land_Reverse
                                    || mLastOrientation == Orientation.Land_Forward
                        )
                        mLastOrientation = Orientation.Port
                    }
                }

            }
        }
        mLandOrientationListener?.enable()
    }

    fun stopWatch() {
        mLandOrientationListener?.disable()
    }

    fun destroy() {
        stopWatch()
        mLandOrientationListener = null
    }

    fun setOnOrientationListener(l: OnOrientationListener) {
        this.mOrientationListener = l
    }


    interface OnOrientationListener {

        /**
         *变为Land_Forward
         *@param fromPort 是否是从竖屏变过来的
         */
        fun changedToLandForwardScape(fromPort: Boolean)

        /**
         * 变为Land_Reverse
         *
         * @param fromPort 是否是从竖屏变过来的
         */
        fun changedToLandReverseScape(fromPort: Boolean)

        /**
         * 变为Port
         *
         * @param fromLand 是否是从横屏变过来的
         */
        fun changedToPortrait(fromLand: Boolean)
    }

}