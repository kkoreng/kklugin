plugins {
    id("kr.kkoreng.kklugin")
}

dependencies {
    implementation(libs.gson)
}


gradlePlugin {
    plugins {
        register("run") {
            id = "kr.kkoreng.kklugin.run"
            implementationClass = "kr.kkoreng.kklugin.run.RunPlugin"
        }
    }
}

