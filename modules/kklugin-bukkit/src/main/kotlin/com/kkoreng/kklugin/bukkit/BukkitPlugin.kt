package com.kkoreng.kklugin.bukkit

import com.kkoreng.kklugin.bukkit.extension.BukkitKkluginExtension
import com.kkoreng.kklugin.bukkit.plugin.tasks.GeneratePluginYamlTask
import com.kkoreng.kklugin.core.Constants
import com.kkoreng.kklugin.core.KkluginPlugin
import com.kkoreng.kklugin.core.setup.tasks.SetupPluginTask
import org.gradle.api.Project

class BukkitPlugin : KkluginPlugin<BukkitKkluginExtension>() {

    override val extensionClass = BukkitKkluginExtension::class.java

    override fun onApply(target: Project, extension: BukkitKkluginExtension) {
        target.tasks.register("generateMetadata", GeneratePluginYamlTask::class.java) { task ->
                    task.group = "kklugin"
            task.extension.set(extension.plugin)
            task.outputFile.set(target.layout.projectDirectory.file("src/main/resources/plugin.yml"))
        }

        target.tasks.named("setupPlugin", SetupPluginTask::class.java) { task ->
            task.repositoryUrl.set(Constants.Paper.REPO_URL)
            task.dependency.set(extension.plugin.minecraftVersion.map { Constants.Paper.API_DEPENDENCY.format(it) })
            task.dependsOn("generateMetadata")
        }

    }
}
