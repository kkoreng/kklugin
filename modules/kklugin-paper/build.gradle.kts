plugins {
    id("com.kkoreng.kklugin")
}

dependencies {
    implementation(project(":modules:kklugin-bukkit"))
}

gradlePlugin {
    plugins {
        register("paper") {
            id = "com.kkoreng.kklugin.paper"
            implementationClass = "com.kkoreng.kklugin.paper.PaperPlugin"
        }
    }
}