pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "kklugin"

includeBuild("build-logic")
include(
    "modules:kklugin-core",
    "modules:kklugin-bukkit",
    "modules:kklugin-paper"
)