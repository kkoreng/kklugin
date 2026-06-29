plugins {
    id("com.kkoreng.kklugin")
    id("com.kkoreng.kklugin.publish")
}

dependencies {
    implementation(libs.gson)
}

gradlePlugin {
    plugins {
        register("run") {
            id = "com.kkoreng.kklugin.run"
            implementationClass = "com.kkoreng.kklugin.run.RunPlugin"
            displayName = "KKlugin Run"
            description = "Gradle plugin for running Minecraft servers during development"
            tags.set(listOf("minecraft", "paper", "bukkit", "run", "server"))
        }
    }
}
