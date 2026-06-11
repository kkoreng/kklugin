package com.kkoreng.kklugin.core

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.kkoreng.kklugin.core.build.tasks.BuildPluginJarTask
import com.kkoreng.kklugin.core.extension.KkluginExtension
import com.kkoreng.kklugin.core.setup.tasks.SetupPluginTask
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class KkluginPlugin<E : KkluginExtension> : Plugin<Project> {

    abstract val extensionClass: Class<E>

    override fun apply(target: Project) {
        val extension = target.extensions.create("kklugin", extensionClass)

        target.pluginManager.apply("java-library")
        target.pluginManager.apply("com.gradleup.shadow")

        target.afterEvaluate {
            target.tasks.named("shadowJar", ShadowJar::class.java) { task ->
                task.onlyIf { extension.build.shadow.get() }
                if (extension.build.minimize.get()) task.minimize()
                extension.build.relocations.get().forEach { (from, to) -> task.relocate(from, to) }
                extension.build.exclude.get().forEach { task.exclude(it) }
            }

        }

        target.tasks.register("buildPlugin", BuildPluginJarTask::class.java) { task ->
            task.group = "kklugin"
            task.extension.set(extension.build)
            task.dependsOn("shadowJar")
        }

        target.tasks.register("setupPlugin", SetupPluginTask::class.java) { task ->
            task.group = "kklugin"

        }

        onApply(target, extension)
    }

    abstract fun onApply(target: Project, extension: E)

}
