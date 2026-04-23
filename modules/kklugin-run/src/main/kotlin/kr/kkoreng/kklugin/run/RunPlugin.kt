package com.kkoreng.kklugin.run

import com.kkoreng.kklugin.run.extension.RunKkluginExtension
import com.kkoreng.kklugin.run.extension.proxy.ProxyServerExtension
import com.kkoreng.kklugin.run.extension.server.ServerExtension
import com.kkoreng.kklugin.run.tasks.setup.SetupProxyTask
import com.kkoreng.kklugin.run.tasks.setup.SetupServerTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware

class RunPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        // kklugin-run을 kklugin-bukkit 또는 kklugin-paper와 함께 사용할 때
        target.pluginManager.withPlugin("com.kkoreng.kklugin.bukkit") { registerAll(target) }
        target.pluginManager.withPlugin("com.kkoreng.kklugin.paper") { registerAll(target) }

        // kklugin-run을 단독으로 사용할 때
        if (target.extensions.findByName("kklugin") == null) {
            val ext = target.extensions.create("kklugin", RunKkluginExtension::class.java)
            registerRunServer(target, ext.server)
            registerRunProxy(target, ext.proxy)
        }
    }

    private fun registerAll(target: Project) {
        val kklugin = target.extensions.getByName("kklugin") as ExtensionAware
        registerRunServer(target, kklugin.extensions.create("runServer",
            ServerExtension::class.java))
        registerRunProxy(target, kklugin.extensions.create("runProxy",
            ProxyServerExtension::class.java))
    }

    private fun registerRunServer(target: Project, serverExt: ServerExtension) {
        target.tasks.register("setupServer", SetupServerTask::class.java) { task ->
            task.group = "kklugin server"
            task.extension.set(serverExt)
        }
    }
    private fun registerRunProxy(target: Project, proxyExt: ProxyServerExtension) {
        target.tasks.register("setupProxy", SetupProxyTask::class.java) { task ->
            task.group = "kklugin server"
            task.extension.set(proxyExt)
        }
    }
}