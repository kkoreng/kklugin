package kr.kkoreng.kklugin.run.platform

interface PlatformDownloadUrlProvider {
    fun resolve(version: String, build: String): String
}