package kr.kkoreng.kklugin.bukkit

import kr.kkoreng.kklugin.bukkit.extension.BukkitKkluginExtension
import kr.kkoreng.kklugin.bukkit.plugin.tasks.GeneratePluginYamlTask
import kr.kkoreng.kklugin.core.KkluginPlugin
import kr.kkoreng.kklugin.core.setup.tasks.SetupPluginTask
import org.gradle.api.Project

class BukkitPlugin : KkluginPlugin<BukkitKkluginExtension>() {

    override val extensionClass = BukkitKkluginExtension::class.java

    override fun onApply(target: Project, extension: BukkitKkluginExtension) {
target.tasks.register("generatePluginYml", GeneratePluginYamlTask::class.java) { task ->
            task.group = "kklugin"
            task.extension.set(extension.plugin)
            task.outputFile.set(target.layout.projectDirectory.file("src/main/resources/plugin.yml"))
        }

        target.tasks.named("setupPlugin", SetupPluginTask::class.java) { task ->
            task.repositoryUrl.set("https://repo.papermc.io/repository/maven-public/")
            task.dependency.set(extension.plugin.minecraftVersion.map { "io.papermc.paper:paper-api:$it-R0.1-SNAPSHOT" })
            task.dependsOn("generatePluginYml")
        }

    }
}
