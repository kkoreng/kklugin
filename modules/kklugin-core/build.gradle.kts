plugins {
    id("kr.kkoreng.kklugin")
    alias(libs.plugins.shadow.jar)
}

dependencies {
    implementation(libs.shadow.gradle.plugin)
}