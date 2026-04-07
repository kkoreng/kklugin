package kr.kkoreng.kklugin.core

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import kr.kkoreng.kklugin.core.extension.KkluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class KkluginPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val extension = target.extensions.create("kklugin", KkluginExtension::class.java)

        target.pluginManager.apply("java-library")
        target.pluginManager.apply("com.gradleup.shadow")
        target.tasks.named("shadowJar", ShadowJar::class.java) { task ->
            task.onlyIf { extension.build.shadow.get() }
            if (extension.build.minimize.get()) task.minimize()
            extension.build.relocations.get().forEach { (from, to) ->
                task.relocate(from, to)
            }
            extension.build.exclude.get().forEach { task.exclude(it) }
        }

        onApply(target, extension)
    }

    abstract fun onApply(target: Project, extension: KkluginExtension)

}
