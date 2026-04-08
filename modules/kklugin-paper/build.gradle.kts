plugins {
    id("kr.kkoreng.kklugin")
}

dependencies {
    implementation(project(":modules:kklugin-bukkit"))
}

gradlePlugin {
    plugins {
        register("paper") {
            id = "kr.kkoreng.kklugin.paper"
            implementationClass = "kr.kkoreng.kklugin.paper.PaperPlugin"
        }
    }
}