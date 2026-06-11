package com.kkoreng.kklugin.paper

import com.kkoreng.kklugin.core.Constants
import com.kkoreng.kklugin.core.KkluginPlugin
import com.kkoreng.kklugin.core.setup.tasks.SetupPluginTask
import com.kkoreng.kklugin.paper.extension.PaperKkluginExtension
import com.kkoreng.kklugin.paper.plugin.tasks.GeneratePaperPluginYamlTask
import org.gradle.api.Project

class PaperPlugin : KkluginPlugin<PaperKkluginExtension>() {

    override val extensionClass = PaperKkluginExtension::class.java

    override fun onApply(target: Project, extension: PaperKkluginExtension) {
        target.tasks.register("generateMetadata", GeneratePaperPluginYamlTask::class.java) { task ->
            task.group = "kklugin"
            task.extension.set(extension.plugin)
            task.outputFile.set(target.layout.projectDirectory.file("src/main/resources/paper-plugin.yml"))
        }

        target.tasks.named("setupPlugin", SetupPluginTask::class.java) { task ->
            task.repositoryUrl.set(Constants.Paper.REPO_URL)
            task.dependency.set(extension.plugin.minecraftVersion.map { Constants.Paper.API_DEPENDENCY.format(it) })
            task.dependsOn("generateMetadata")
        }
    }
}
