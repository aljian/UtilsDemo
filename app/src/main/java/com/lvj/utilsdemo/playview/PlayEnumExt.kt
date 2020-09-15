package com.lvj.utilsdemo.playview

/*
    播放中 各种状态类集合 方便统一管理
 */

/**
 * 全屏 非全屏
 */
enum class ScreenMode {

    /**
     * 全屏
     */
    Full,

    /**
     * 小屏
     */
    Port
}

/**
 * 屏幕方向
 */
enum class Orientation {

    /**
     * 竖屏
     */
    Port,

    /**
     * 横屏,正向
     */
    Land_Forward,

    /**
     * 横屏,反向
     */
    Land_Reverse
}

/**
 * 播放状态
 */
enum class PlayState {

    Playing, NotPlaying
}

/**
 * 控制UI显示隐藏
 */
enum class HideType {

    /**
     * 正常情况下的隐藏
     */
    Normal,

    /**
     * 播放结束 错误时候的隐藏
     */
    End
}
