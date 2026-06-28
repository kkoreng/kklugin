package com.kkoreng.kklugin.core

object Constants {

    object Defaults {
        const val BUILD_VERSION = "latest"
        const val TASK_GROUP = "kklugin"
    }

    object Paper {
        const val REPO_URL = "https://repo.papermc.io/repository/maven-public/"
        const val API_DEPENDENCY = "io.papermc.paper:paper-api:%s-R0.1-SNAPSHOT"
    }

    object Platform {
        const val PAPER_MC_API_BASE = "https://fill.papermc.io/v3/projects"
        const val PURPUR_API_BASE = "https://api.purpurmc.org/v2/purpur"
    }

    object FileNames {
        const val SERVER_JAR = "server.jar"
        const val EULA_TXT = "eula.txt"
        const val FORWARDING_SECRET = "forwarding.secret"
        const val VELOCITY_TOML = "velocity.toml"
        const val SERVER_PROPERTIES = "server.properties"
        const val PAPER_GLOBAL_YML = "paper-global.yml"
        const val GITIGNORE = ".gitignore"
    }

    object Templates {
        const val VELOCITY_TOML = "/templates/velocity.toml.template"
        const val SERVER_PROPERTIES = "/templates/server.properties.template"
        const val PAPER_GLOBAL_YML = "/templates/paper-global.yml.template"
    }


}