package com.lvj.utilsdemo.audio

import android.media.MediaPlayer

class ManagedMediaPlayer : MediaPlayer, MediaPlayer.OnCompletionListener {

    enum class Status {
        IDLE, INITIALIZED, STARTED, PAUSED, STOPPED, COMPLETED
    }

    private var mState: Status = Status.IDLE

    private var mOnCompletionListener: OnCompletionListener? = null

    constructor() : super() {
        mState = Status.IDLE
        setOnCompletionListener(this)
    }

    override fun reset() {
        super.reset()
        mState = Status.IDLE
    }

    override fun setDataSource(path: String?) {
        super.setDataSource(path)
        mState = Status.INITIALIZED
    }

    override fun start() {
        super.start()
        mState = Status.STARTED
    }

    override fun stop() {
        super.stop()
        mState = Status.STOPPED
    }

    override fun pause() {
        super.pause()
        mState = Status.PAUSED
    }

    override fun setOnCompletionListener(listener: OnCompletionListener?) {
        this.mOnCompletionListener = listener
    }


    override fun onCompletion(mp: MediaPlayer?) {
        mState = Status.COMPLETED
        mOnCompletionListener?.onCompletion(mp)
    }

    fun setState(mState: Status) {
        this.mState = mState
    }

    fun getState(): Status {
        return mState
    }

    fun isComplete(): Boolean = mState == Status.COMPLETED
}