package com.lvj.utilsdemo.audio

import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import androidx.media.MediaBrowserServiceCompat
import java.util.*

class MusicService : MediaBrowserServiceCompat() {

    companion object {
        private const val MEDIA_ID_EMPTY_ROOT = "__EMPTY_ROOT__"
        private const val MEDIA_ID_ROOT = "__ROOT__"
    }

    override fun onGetRoot(clientPackageName: String, clientUid: Int, rootHints: Bundle?): BrowserRoot? {

        return if (clientPackageName != packageName) {
            BrowserRoot(MEDIA_ID_EMPTY_ROOT, null)
        } else BrowserRoot(MEDIA_ID_ROOT, null)

    }

    override fun onLoadChildren(parentId: String, result: Result<MutableList<MediaBrowserCompat.MediaItem>>) {
        if (MEDIA_ID_EMPTY_ROOT == parentId) {
            result.sendResult(ArrayList())
        }
    }


}