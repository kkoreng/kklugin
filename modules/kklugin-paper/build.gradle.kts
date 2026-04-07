plugins {
    id("kr.kkoreng.kklugin")
}

dependencies {
    implementation(project(":modules:kklugin-bukkit"))
}

gradlePlugin {
    plugins {
        register("paper-plugin") {
            id = "kr.kkoreng.kklugin.paper-plugin"
            implementationClass = "kr.kkoreng.kklugin.paper.PaperPlugin"
        }
    }
}