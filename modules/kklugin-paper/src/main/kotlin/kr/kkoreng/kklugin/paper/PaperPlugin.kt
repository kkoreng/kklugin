package kr.kkoreng.kklugin.paper

import kr.kkoreng.kklugin.core.KkluginPlugin
import kr.kkoreng.kklugin.core.extension.KkluginExtension
import kr.kkoreng.kklugin.paper.plugin.extension.PaperPluginExtension
import kr.kkoreng.kklugin.paper.plugin.tasks.GeneratePaperPluginYamlTask
import org.gradle.api.Project

class PaperPlugin : KkluginPlugin() {
    override fun onApply(target: Project, extension: KkluginExtension) {

    }
}
