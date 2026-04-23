package com.kkoreng.kklugin.run.tasks.setup

import org.gradle.api.DefaultTask
import java.io.File
import java.net.URL

abstract class AbstractSetupTask: DefaultTask() {

    protected fun directorySetup(serverDir: File) {
        serverDir.mkdirs()
        addGitignore("run/")
    }

    protected fun download(downloadUrl: String, jarFile: File) {
        URL(downloadUrl).openStream().use { input ->
            jarFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }

    protected fun eulaSetup(acceptEula: Boolean, serverDir: File) {
        if (!acceptEula) return
        serverDir.resolve("eula.txt").writeText("eula=true")
    }

    protected fun addGitignore(entry: String) {
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
}