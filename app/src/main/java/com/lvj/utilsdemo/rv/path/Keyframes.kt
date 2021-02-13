package com.lvj.utilsdemo.rv.path

import android.graphics.Path
import android.graphics.PathMeasure
import androidx.annotation.FloatRange
import kotlin.math.atan2


class Keyframes {

    private val PRECISION = .5f

    //path上所有X轴坐标点
    private var mX: FloatArray? = null

    //path上所有Y轴坐标点
    private var mY: FloatArray? = null

    //path上每个左边对应的角度
    private var mAngle: FloatArray? = null

    private var mTemp: PosTan? = null

    private var mMaxX = 0f
    private var mMaxY = 0f
    private var mNumPoints = 0


    constructor(path: Path) {
        initPath(path)
        mTemp = PosTan()
    }

    private fun initPath(path: Path) {
        if (path.isEmpty) throw NullPointerException("path is empty ! ")

        val pathMeasure = PathMeasure(path, false)
        mX = FloatArray(0)
        mY = FloatArray(0)
        mAngle = FloatArray(0)
        do {
            val pathLength = pathMeasure.length
            val numPoints = ((pathLength / PRECISION) + 1).toInt()
            val x = FloatArray(numPoints)
            val y = FloatArray(numPoints)
            val angle = FloatArray(numPoints)
            val position = FloatArray(2)
            val tangent = FloatArray(2)
            for (i in 0 until numPoints) {
                val distance = (i * pathLength) / (numPoints - 1)
                pathMeasure.getPosTan(distance, position, tangent)
                if (position[0] > mMaxX) {
                    mMaxX = position[0]
                }
                if (position[1] > mMaxY) {
                    mMaxY = position[1]
                }
                x[i] = position[0]
                y[i] = position[1]
                angle[i] = fixAngle((atan2(tangent[1], tangent[0]) * 180f / Math.PI).toFloat())
            }
            mNumPoints += numPoints

            mX?.let {
                val tmpX = FloatArray(it.size + x.size)
                System.arraycopy(it, 0, tmpX, 0, it.size)
                System.arraycopy(x, 0, tmpX, it.size, x.size)
                mX = tmpX
            }

            mY?.let {
                val tmpY = FloatArray(it.size + y.size)
                System.arraycopy(it, 0, tmpY, 0, it.size)
                System.arraycopy(y, 0, tmpY, it.size, y.size)
                mY = tmpY
            }

            mAngle?.let {
                val tmpAngle = FloatArray(it.size + angle.size)
                System.arraycopy(it, 0, tmpAngle, 0, it.size)
                System.arraycopy(angle, 0, tmpAngle, it.size, angle.size)
                mAngle = tmpAngle
            }

        } while (pathMeasure.nextContour())

    }

    /**
     * 调整旋转角度 使其在0~360之间
     * @param rotation 当前角度
     * @return 调整后的角度
     */
    private fun fixAngle(rotation: Float): Float {
        var rotation = rotation
        val angle = 360f
        if (rotation < 0) {
            rotation += angle
        }
        if (rotation > angle) {
            rotation %= angle
        }
        return rotation
    }

    fun getMaxX() = mMaxX.toInt()
    fun getMaxY() = mMaxY.toInt()

    fun getValue(@FloatRange(from = 0f.toDouble(), to = 1f.toDouble()) fraction: Float): PosTan? {
        return if (fraction >= 1f || fraction < 0
            || mX == null || mY == null || mAngle == null || mTemp == null
        ) {
            null
        } else {
            val index = (mNumPoints * fraction).toInt()
            mTemp!!.set(mX!![index], mY!![index], mAngle!![index])
            mTemp
        }
    }

    fun getValue(index: Int): PosTan? {
        return if (mTemp == null || mX == null || mY == null || mAngle == null) {
            null
        } else {
            mTemp!!.set(mX!![index], mY!![index], mAngle!![index])
            mTemp
        }
    }

    fun getPathLength(): Int {
        return mNumPoints / 2
    }

    fun release() {
        mX = null
        mY = null
        mAngle = null
        mTemp = null
    }

}