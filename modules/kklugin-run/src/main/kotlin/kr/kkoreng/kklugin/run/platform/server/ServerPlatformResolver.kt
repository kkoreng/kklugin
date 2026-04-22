package kr.kkoreng.kklugin.run.platform.server

import kr.kkoreng.kklugin.run.enums.ServerPlatform
import kr.kkoreng.kklugin.run.platform.server.provider.PaperMCDownloadUrlProvider
import kr.kkoreng.kklugin.run.platform.server.provider.PurpurDownloadUrlProvider

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