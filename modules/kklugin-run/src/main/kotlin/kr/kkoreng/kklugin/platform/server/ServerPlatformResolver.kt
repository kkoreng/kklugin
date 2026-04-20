package kr.kkoreng.kklugin.platform.server

import kr.kkoreng.kklugin.enums.ServerPlatform
import kr.kkoreng.kklugin.platform.server.provider.PaperMCDownloadUrlProvider

class ServerPlatformResolver {
    private val providers = mapOf(
        ServerPlatform.PAPER to PaperMCDownloadUrlProvider("paper"),
        ServerPlatform.FOLIA to PaperMCDownloadUrlProvider("folia"),
        ServerPlatform.PURPUR to TODO(),
        ServerPlatform.PUFFERFISH to TODO()
    )

    fun resolve(platform: ServerPlatform, version: String): String {
        return providers[platform]?.resolve(version)
            ?: error("Unsupported platform: $platform")
    }

}