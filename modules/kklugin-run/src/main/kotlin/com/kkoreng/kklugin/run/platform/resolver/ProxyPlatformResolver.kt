package com.kkoreng.kklugin.run.platform.resolver

import com.kkoreng.kklugin.run.enums.ProxyPlatform
import com.kkoreng.kklugin.run.platform.impl.PaperMCDownloadUrlProvider

class ProxyPlatformResolver {
    private val providers = mapOf(
        ProxyPlatform.VELOCITY to PaperMCDownloadUrlProvider("velocity")
        )

    fun resolve(platform: ProxyPlatform, version: String, build: String): String {
        return providers[platform]?.resolve(version, build)
            ?: error("Unsupported platform: $platform")
    }
}