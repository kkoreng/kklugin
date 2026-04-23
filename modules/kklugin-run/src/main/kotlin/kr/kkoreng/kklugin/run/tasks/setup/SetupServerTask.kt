package com.kkoreng.kklugin.run.tasks.setup

import com.kkoreng.kklugin.run.extension.server.ServerExtension
import com.kkoreng.kklugin.run.platform.resolver.ServerPlatformResolver
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction

abstract class SetupServerTask: AbstractSetupTask() {

    @get:Nested abstract val extension: Property<ServerExtension>

    private fun downloadServer(ext: ServerExtension) {
        val version = ext.minecraftVersion.get()
        val build = ext.buildVersion.orNull ?: "latest"
        val platform = ext.platform.get()
        val serverDir = project.file(ext.serverDirectory.get())
        val jarFile = serverDir.resolve("server.jar")

        if (jarFile.exists()) {
            println("server.jar already exists, skipping download.")
            return
        }

        val url = ServerPlatformResolver().resolve(platform, version, build)
        download(url, jarFile)
    }

    @TaskAction
    fun execute() {
        val ext = extension.get()
        val serverDir = ext.serverDirectory.orNull?.let { project.file(it) } ?: return

        directorySetup(serverDir)
        downloadServer(ext)
        eulaSetup(ext.acceptEula.get(), serverDir)

    }
}