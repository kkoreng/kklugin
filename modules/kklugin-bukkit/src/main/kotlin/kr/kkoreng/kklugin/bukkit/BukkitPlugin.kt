package kr.kkoreng.kklugin.bukkit

import kr.kkoreng.kklugin.bukkit.extension.BukkitPluginExtension
import kr.kkoreng.kklugin.bukkit.tasks.GeneratePluginYamlTask
import kr.kkoreng.kklugin.core.GenerateMetadataTask
import kr.kkoreng.kklugin.core.KkluginPlugin
import kr.kkoreng.kklugin.core.extension.KkluginExtension
import org.gradle.api.Project

class BukkitPlugin : KkluginPlugin() {
    override fun onApply(target: Project, extension: KkluginExtension) {
        val pluginExt = target.extensions.create("plugin", BukkitPluginExtension::class.java)

        target.tasks.register("generatePluginYml", GeneratePluginYamlTask::class.java) { task ->
            task.extension.set(pluginExt)
            task.outputFile.set(target.layout.buildDirectory.file("resources/main/plugin.yml"))
        }

        target.tasks.named("processResources") {
            it.dependsOn("generatePluginYml")
        }
    }
}