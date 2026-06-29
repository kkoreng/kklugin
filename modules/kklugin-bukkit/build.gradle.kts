plugins {
    id("com.kkoreng.kklugin")
    id("com.kkoreng.kklugin.publish")
}

gradlePlugin {
    plugins {
        register("bukkit") {
            id = "com.kkoreng.kklugin.bukkit"
            implementationClass = "com.kkoreng.kklugin.bukkit.BukkitPlugin"
            displayName = "KKlugin Bukkit"
            description = "Gradle plugin for Bukkit/Spigot Minecraft plugin development"
            tags.set(listOf("minecraft", "bukkit", "spigot"))
        }
    }
}
