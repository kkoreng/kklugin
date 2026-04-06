plugins {
    id("kr.kkoreng.kklugin")
    alias(libs.plugins.shadow.jar)
}

dependencies {
    compileOnly(libs.shadow.gradle.plugin)
}