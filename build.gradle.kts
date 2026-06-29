plugins {
    base
}

subprojects {
    group = rootProject.group
    version = rootProject.version
}

tasks.register("release") {
    group = "kklugin"
    description = "Tag and push current version"
    doLast {
        val version = rootProject.version.toString()
        listOf(
            listOf("git", "tag", "v$version"),
            listOf("git", "push", "origin", "v$version")
        ).forEach { cmd ->
            ProcessBuilder(cmd).inheritIO().start().waitFor()
        }
    }
}

