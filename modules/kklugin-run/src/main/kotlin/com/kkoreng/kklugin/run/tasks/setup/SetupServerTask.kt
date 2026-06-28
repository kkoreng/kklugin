package com.kkoreng.kklugin.run.tasks.setup

import com.kkoreng.kklugin.core.Constants
import com.kkoreng.kklugin.run.extension.server.ServerExtension
import com.kkoreng.kklugin.run.platform.resolver.ServerPlatformResolver
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction

abstract class SetupServerTask: AbstractSetupTask() {

    @get:Nested abstract val extension: Property<ServerExtension>

    private fun downloadServer(ext: ServerExtension, serverDir: java.io.File) {
        val jarFile = serverDir.resolve(Constants.FileNames.SERVER_JAR)
        if (jarFile.exists()) {
            println("server.jar already exists, skipping download.")
            return
        }
        val url = ServerPlatformResolver().resolve(
            ext.platform.get(),
            ext.minecraftVersion.get(),
            ext.buildVersion.orNull ?: Constants.Defaults.BUILD_VERSION
        )
        download(url, jarFile)
    }

    @TaskAction
    fun execute() {
        val ext = extension.get()
        val serverDir = ext.serverDirectory.orNull?.let { project.file(it) } ?: return

        directorySetup(serverDir)
        downloadServer(ext, serverDir)
        eulaSetup(ext.acceptEula.get(), serverDir)
    }
}