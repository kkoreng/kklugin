plugins {
    id("com.kkoreng.kklugin")
}

dependencies {
    implementation(libs.gson)
}


gradlePlugin {
    plugins {
        register("run") {
            id = "com.kkoreng.kklugin.run"
            implementationClass = "com.kkoreng.kklugin.run.RunPlugin"
        }
    }
}

