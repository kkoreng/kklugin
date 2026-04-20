package kr.kkoreng.kklugin.core

object Constants {

    object Paper { // 플러그인 개발용 repo/dependency
        const val REPO_URL = "https://repo.papermc.io/repository/maven-public/"
        const val API_DEPENDENCY = "io.papermc.paper:paper-api:%s-R0.1-SNAPSHOT"
    }

    object ServerPlatform { // 서버 jar 다운로드용 API
        const val PAPER_MC_API_BASE = "https://api.papermc.io/v2/projects"
        const val PURPUR_API_BASE = "https://api.pl3x.net/v2/purpur"
    }
}