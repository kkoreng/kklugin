package kr.kkoreng.kklugin.platform.server.provider

import com.google.gson.JsonParser
import kr.kkoreng.kklugin.core.Constants
import kr.kkoreng.kklugin.platform.PlatformDownloadUrlProvider
import java.net.URL

class PaperMCDownloadUrlProvider(
    private val projectName: String
): PlatformDownloadUrlProvider {

    /**
     * PaperMC API를 요청 -> String URL 반환 -> JSON 파싱 -> lastest build 번호 추출 -> 다운로드 URL 조합
     */
    override fun resolve(version: String): String {
        val apiBase = Constants.ServerPlatform.PAPER_MC_API_BASE
        val buildsJson = URL("$apiBase/$projectName/versions/$version/builds").readText()
        val builds = JsonParser.parseString(buildsJson).asJsonObject
            .getAsJsonArray("builds")
        val latestBuild = builds[builds.size() - 1].asJsonObject.get("build").asInt

        return "$apiBase/$projectName/versions/$version/builds/$latestBuild/downloads/$projectName-$version-$latestBuild.jar"
    }
}