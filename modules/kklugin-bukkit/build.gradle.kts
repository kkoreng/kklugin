plugins {
    id("kr.kkoreng.kklugin")
}

gradlePlugin {
    website.set("")
    vcsUrl.set("")
    plugins {
        register("bukkit") {
            id = "kr.kkoreng.kklugin.bukkit"
            implementationClass = "kr.kkoreng.kklugin.bukkit.BukkitPlugin"
        }
    }
}
