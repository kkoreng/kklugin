package kr.kkoreng.kklugin.bukkit

import kr.kkoreng.kklugin.bukkit.plugin.extension.PluginExtension
import kr.kkoreng.kklugin.bukkit.plugin.tasks.GeneratePluginYamlTask
import kr.kkoreng.kklugin.core.KkluginPlugin
import kr.kkoreng.kklugin.core.extension.KkluginExtension
import org.gradle.api.Project

class BukkitPlugin : KkluginPlugin() {
    override fun onApply(target: Project, extension: KkluginExtension) {
        val pluginExt = target.extensions.create("plugin", PluginExtension::class.java)

        target.tasks.register("generatePluginYml", GeneratePluginYamlTask::class.java) { task ->
            task.extension.set(pluginExt)
            task.outputFile.set(target.layout.projectDirectory.file("src/main/resources/plugin.yml"))
        }
    }
}