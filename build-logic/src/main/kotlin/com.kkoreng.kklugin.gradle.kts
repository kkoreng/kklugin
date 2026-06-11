plugins {
    kotlin("jvm")
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

dependencies {
    if (project.name != "kklugin-core") { // kklugin-core가 자기 자신을 참조하는 경우 방지
        implementation(project(":modules:kklugin-core"))
    }

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}