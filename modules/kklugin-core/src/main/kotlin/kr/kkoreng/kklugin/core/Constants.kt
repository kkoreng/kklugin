package com.kkoreng.kklugin.core

object Constants {

    object Paper { // 플러그인 개발용 repo/dependency
        const val REPO_URL = "https://repo.papermc.io/repository/maven-public/"
        const val API_DEPENDENCY = "io.papermc.paper:paper-api:%s-R0.1-SNAPSHOT"
    }

    object ServerPlatform { // 서버 jar 다운로드용 API
        const val PAPER_MC_API_BASE = "https://fill.papermc.io/v3/projects"
        const val PURPUR_API_BASE = "https://api.purpurmc.org/v2/purpur"
    }
}