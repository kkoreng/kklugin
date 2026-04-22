package kr.kkoreng.kklugin.run.platform.server.provider

import kr.kkoreng.kklugin.core.Constants
import kr.kkoreng.kklugin.run.platform.PlatformDownloadUrlProvider

class PurpurDownloadUrlProvider : PlatformDownloadUrlProvider {

    override fun resolve(version: String, build: String): String {
        val apiBase = Constants.ServerPlatform.PURPUR_API_BASE
        return if (build != null) { // 빌드 넘버 지정된 경우
                "$apiBase/$version/$build/download"
            } else { // 빌드 넘버 지정 안된 경우 -> latest 빌드 url 반환
                "$apiBase/$version/latest/download"
            }
    }
}