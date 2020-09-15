package com.lvj.utilsdemo.playview

interface PlayActionListener {

    /**
     * 左上角返回键事件
     */
    fun onBackClick()


    /**
     * 屏幕方向改变
     *
     * @param from        从横屏切换为竖屏, 从竖屏切换为横屏
     * @param currentMode 当前屏幕类型
     */
    fun orientationChange(from: Boolean, currentMode: ScreenMode)

}