package kr.kkoreng.kklugin.run

import kr.kkoreng.kklugin.run.extension.RunKkluginExtension
import kr.kkoreng.kklugin.run.extension.server.ServerExtension
import kr.kkoreng.kklugin.run.tasks.setup.SetupServerTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware

class RunPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        // bukkit과 함께 쓸 때 -> bukkit 플러그인이 먼저 적용되어야 함
        target.pluginManager.withPlugin("kr.kkoreng.kklugin.bukkit") {
            registerRunServer(target, (target.extensions.getByName("kklugin") as ExtensionAware)
                .extensions.create("runServer", ServerExtension::class.java))
        }

        // paper와 함께 쓸 때 -> paper 플러그인이 먼저 적용되어야 함
        target.pluginManager.withPlugin("kr.kkoreng.kklugin.paper") {
            registerRunServer(target, (target.extensions.getByName("kklugin") as ExtensionAware)
                .extensions.create("runServer", ServerExtension::class.java))
        }

        // 단독으로 쓸 때 ->
        if (target.extensions.findByName("kklugin") == null) {
            val ext = target.extensions.create("kklugin", RunKkluginExtension::class.java)
            registerRunServer(target, ext.server)
        }
    }

    private fun registerRunServer(target: Project, serverExt: ServerExtension) {
        target.tasks.register("setupServer", SetupServerTask::class.java) { task ->
            task.group = "kklugin server"
            task.extension.set(serverExt)
        }
    }
}