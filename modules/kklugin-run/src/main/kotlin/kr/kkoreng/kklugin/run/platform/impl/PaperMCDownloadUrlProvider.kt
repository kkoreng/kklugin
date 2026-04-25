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
        val apiBase = Constants.Platform.PAPER_MC_API_BASE
        val buildJson = URL("$apiBase/$projectName/versions/$version/builds/$build").readText()

        return JsonParser.parseString(buildJson)
            .asJsonObject
            .getAsJsonObject("downloads")
            .getAsJsonObject("server:default")
            .get("url").asString
    }
}