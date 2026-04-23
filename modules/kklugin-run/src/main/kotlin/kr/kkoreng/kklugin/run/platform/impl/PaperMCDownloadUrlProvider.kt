package com.kkoreng.kklugin.run.platform.impl

import com.google.gson.JsonParser
import com.kkoreng.kklugin.core.Constants
import com.kkoreng.kklugin.run.platform.PlatformDownloadUrlProvider
import java.net.URL

class PaperMCDownloadUrlProvider(
    private val projectName: String
): PlatformDownloadUrlProvider {

    /**
     * PaperMC Fill API 요청 -> JSON 파싱 -> 다운로드 URL 추출
     *
     * @param version: minecraft version, build: build number of platform
     * @return download URL of server jar
     */
    override fun resolve(version: String, build: String): String {
        val apiBase = Constants.ServerPlatform.PAPER_MC_API_BASE
        val buildJson =
            if (build != null) { // 빌드 넘버 지정된 경우
                URL("$apiBase/$projectName/versions/$version/builds/$build").readText()
            } else { // 빌드 넘버 지정 안된 경우 -> latest 빌드
                URL("$apiBase/$projectName/versions/$version/builds/latest").readText()
            }

        return JsonParser.parseString(buildJson) // JSON 파싱 -> 다운로드 URL 추출
            .asJsonObject
            .getAsJsonObject("downloads")
            .getAsJsonObject("server:default")
            .get("url").asString
    }
}