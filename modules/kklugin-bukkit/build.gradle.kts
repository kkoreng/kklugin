plugins {
    id("com.kkoreng.kklugin")
}

gradlePlugin {
    website.set("")
    vcsUrl.set("")
    plugins {
        register("bukkit") {
            id = "com.kkoreng.kklugin.bukkit"
            implementationClass = "com.kkoreng.kklugin.bukkit.BukkitPlugin"
        }
    }
}
