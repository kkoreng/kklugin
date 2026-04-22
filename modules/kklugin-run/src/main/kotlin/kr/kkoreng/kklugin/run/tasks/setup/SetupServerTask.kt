package kr.kkoreng.kklugin.run.tasks.setup

import kr.kkoreng.kklugin.run.extension.server.ServerExtension
import kr.kkoreng.kklugin.run.platform.server.ServerPlatformResolver
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.net.URL

abstract class SetupServerTask: DefaultTask() {

    @get:Nested abstract val extension: Property<ServerExtension>

    fun directorySetup(ext: ServerExtension) {
        val serverDir = ext.serverDirectory.orNull?.let { project.file(it) } ?: return
        serverDir.mkdirs()
        addGitignore("run/")
    }

    private fun downloadServer(ext: ServerExtension) {
        val version = ext.minecraftVersion.get()
        val build = ext.build.orNull ?: "latest"
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

    private fun download(downloadUrl: String, jarFile: File) {
        URL(downloadUrl).openStream().use { input ->
            jarFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }

    private fun eulaSetup(ext: ServerExtension) {
        if (!ext.acceptEula.get()) return
        val serverDir = project.file(ext.serverDirectory.get())
        serverDir.resolve("eula.txt").writeText("eula=true")
    }

    private fun addGitignore(entry: String) {
        val header = "### Kklugin ###"
        val gitignore = project.rootDir.resolve(".gitignore")

        if (!gitignore.exists()) {
            gitignore.writeText("$header\n$entry")
            return
        }

        var content = gitignore.readText()

        if (content.contains(entry)) return

        content = if (content.contains(header)) {
            content.replace(header, "$header\n$entry")
        } else {
            "$content\n$header\n$entry"
        }

        gitignore.writeText(content)
    }

    @TaskAction
    fun execute() {
        val ext = extension.get()

        directorySetup(ext)
        downloadServer(ext)
        eulaSetup(ext)
    }
}