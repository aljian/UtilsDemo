package com.lvj.utilsdemo.view.point

import android.animation.TypeEvaluator

class PointEvaluator : TypeEvaluator<Point> {

    override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {
        val start = startValue.radius
        val end = endValue.radius
        return Point(start + fraction * (end - start))
    }


}