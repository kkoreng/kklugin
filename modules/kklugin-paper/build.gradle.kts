plugins {
    id("com.kkoreng.kklugin")
    id("com.kkoreng.kklugin.publish")
}

dependencies {
    implementation(project(":modules:kklugin-bukkit"))
}

gradlePlugin {
    plugins {
        register("paper") {
            id = "com.kkoreng.kklugin.paper"
            implementationClass = "com.kkoreng.kklugin.paper.PaperPlugin"
            displayName = "KKlugin Paper"
            description = "Gradle plugin for Paper Minecraft plugin development"
            tags.set(listOf("minecraft", "paper", "bukkit"))
        }
    }
}
