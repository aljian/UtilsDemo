package com.lvj.utilsdemo.rv.path

import android.graphics.PointF

class PosTan : PointF {

    /**
     *在路径上的位置 (百分比)
     */
    var fraction = 0f

    /**
     * item对应的索引
     */
    var index = 0

    /**
     *item旋转角度
     */
    private var angle = 0f

    constructor()

    constructor(index: Int, x: Float, y: Float, angle: Float) : super(x, y) {
        this.angle = angle
        this.index = index
    }

    constructor(p: PosTan, index: Int, fraction: Float) : this(index, p.x, p.y, p.angle) {
        this.fraction = fraction;
    }

    fun set(x: Float, y: Float, angle: Float) {
        this.x = x
        this.y = y
        this.angle = angle
    }

    fun getChildAngle() = angle - 90f

    override fun toString(): String {
        return "x: $x\t y: $y\t angle: $angle"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        return if (other is PosTan) {
            index == other.index
        } else {
            this === other
        }
    }
}