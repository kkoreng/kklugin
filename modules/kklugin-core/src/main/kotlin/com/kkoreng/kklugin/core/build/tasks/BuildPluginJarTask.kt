package com.kkoreng.kklugin.core.build.tasks

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.kkoreng.kklugin.core.build.extension.BuildPluginJarExtension
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.bundling.Jar

abstract class BuildPluginJarTask: DefaultTask() {

    @get:Nested abstract val extension: Property<BuildPluginJarExtension>

    @TaskAction
    fun buildPlugin() {
        val ext = extension.get()

        val outputDir = ext.outputDirectory.orNull?.let { project.file(it) }
            ?: error("[kklugin] build.outputDirectory가 설정되지 않았습니다. build.gradle.kts에서 kklugin { build { outputDirectory.set(\"...\") } } 를 설정해주세요.")

        val jarTask = if (ext.shadow.get()) {
            project.pluginManager.apply("com.gradleup.shadow")
            project.tasks.named("shadowJar", ShadowJar::class.java).get()
        } else {
            project.tasks.named("jar", Jar::class.java).get()
        }

        val jarFile = jarTask.archiveFile.get().asFile
        outputDir.mkdirs()
        jarFile.copyTo(outputDir.resolve(jarFile.name), overwrite = true)
    }

}