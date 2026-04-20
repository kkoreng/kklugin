package kr.kkoreng.kklugin.platform

interface PlatformDownloadUrlProvider {
    fun resolve(version: String): String
}