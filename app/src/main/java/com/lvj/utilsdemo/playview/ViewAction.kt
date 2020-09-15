package com.lvj.utilsdemo.playview

interface ViewAction {

    /**
     * 重置
     */
    fun reset()

    /**
     * ui显示
     */
    fun show()

    /**
     * ui 隐藏
     */
    fun hide(hideType: HideType)

    /**
     * 全屏或者恢复
     */
    fun setScreenModeStatus(mode: ScreenMode)


}