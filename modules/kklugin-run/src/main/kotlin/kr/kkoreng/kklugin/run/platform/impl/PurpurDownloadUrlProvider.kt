package com.kkoreng.kklugin.run.platform.impl

import com.kkoreng.kklugin.core.Constants
import com.kkoreng.kklugin.run.platform.PlatformDownloadUrlProvider

class PurpurDownloadUrlProvider : PlatformDownloadUrlProvider {

    override fun resolve(version: String, build: String): String {
        val apiBase = Constants.Platform.PURPUR_API_BASE
        return "$apiBase/$version/$build/download"
    }
}