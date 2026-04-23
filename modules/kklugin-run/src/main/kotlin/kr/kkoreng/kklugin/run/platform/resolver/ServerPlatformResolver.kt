package com.kkoreng.kklugin.run.platform.resolver

import com.kkoreng.kklugin.run.enums.ServerPlatform
import com.kkoreng.kklugin.run.platform.impl.PaperMCDownloadUrlProvider
import com.kkoreng.kklugin.run.platform.impl.PurpurDownloadUrlProvider

class ServerPlatformResolver {
    private val providers = mapOf(
        ServerPlatform.PAPER to PaperMCDownloadUrlProvider("paper"),
        ServerPlatform.FOLIA to PaperMCDownloadUrlProvider("folia"),
        ServerPlatform.PURPUR to PurpurDownloadUrlProvider()
    )

    fun resolve(platform: ServerPlatform, version: String, build: String): String {
        return providers[platform]?.resolve(version, build)
            ?: error("Unsupported platform: $platform")
    }

}