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
        val isWindows = System.getProperty("os.name").lowercase().contains("win")
        listOf(
            listOf("git", "tag", "v$version"),
            listOf("git", "push", "origin", "v$version")
        ).forEach { cmd ->
            val fullCmd = if (isWindows) listOf("cmd", "/c") + cmd else cmd
            val result = ProcessBuilder(fullCmd).inheritIO().start().waitFor()
            check(result == 0) { "Command failed: $cmd" }
        }
    }
}

