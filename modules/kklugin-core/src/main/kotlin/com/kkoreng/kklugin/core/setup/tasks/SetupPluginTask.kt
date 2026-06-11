package com.kkoreng.kklugin.core.setup.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class SetupPluginTask : DefaultTask() {

    @get:Input abstract val repositoryUrl: Property<String>
    @get:Input abstract val dependency: Property<String>

    @TaskAction
    fun setupPlugin() {
        val buildGradleFile = project.buildFile
        if (!buildGradleFile.exists()) return

        val repo = repositoryUrl.get()
        val dep = dependency.get()
        val isKts = buildGradleFile.name.endsWith(".gradle.kts")

        var text = buildGradleFile.readText()

        if (!text.contains(repo)) {
            val repoLine = if (isKts) "    maven(\"$repo\")" else "    maven { url '$repo' }"
            val lastIndex = text.lastIndexOf("repositories {")
            text = if (lastIndex != -1) {
                text.substring(0, lastIndex + "repositories {".length) +
                    "\n$repoLine" +
                    text.substring(lastIndex + "repositories {".length)
            } else {
                text + "\nrepositories {\n$repoLine\n}\n"
            }
        }

        if (!text.contains(dep)) {
            val depLine = if (isKts) "    compileOnly(\"$dep\")" else "    compileOnly \"$dep\""
            val lastIndex = text.lastIndexOf("dependencies {")
            text = if (lastIndex != -1) {
                text.substring(0, lastIndex + "dependencies {".length) +
                    "\n$depLine" +
                    text.substring(lastIndex + "dependencies {".length)
            } else {
                text + "\ndependencies {\n$depLine\n}\n"
            }
        }

        buildGradleFile.writeText(text)
    }
}
