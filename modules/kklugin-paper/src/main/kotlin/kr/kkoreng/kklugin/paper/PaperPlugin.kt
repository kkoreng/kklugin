package kr.kkoreng.kklugin.paper

import kr.kkoreng.kklugin.core.KkluginPlugin
import kr.kkoreng.kklugin.core.setup.tasks.SetupPluginTask
import kr.kkoreng.kklugin.paper.extension.PaperKkluginExtension
import kr.kkoreng.kklugin.paper.plugin.tasks.GeneratePaperPluginYamlTask
import org.gradle.api.Project

class PaperPlugin : KkluginPlugin<PaperKkluginExtension>() {

    override val extensionClass = PaperKkluginExtension::class.java

    override fun onApply(target: Project, extension: PaperKkluginExtension) {
        target.tasks.register("generateMetadata", GeneratePaperPluginYamlTask::class.java) { task ->
            task.group = "kklugin plugin"
            task.extension.set(extension.plugin)
            task.outputFile.set(target.layout.projectDirectory.file("src/main/resources/paper-plugin.yml"))
        }

        target.tasks.named("setupPlugin", SetupPluginTask::class.java) { task ->
            task.repositoryUrl.set("https://repo.papermc.io/repository/maven-public/")
            task.dependency.set(extension.plugin.minecraftVersion.map { "io.papermc.paper:paper-api:$it-R0.1-SNAPSHOT" })
            task.dependsOn("generateMetadata")
        }
    }
}
