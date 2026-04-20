package kr.kkoreng.kklugin.tasks.setup

import kr.kkoreng.kklugin.extension.server.ServerExtension
import kr.kkoreng.kklugin.platform.server.ServerPlatformResolver
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction
import java.net.URL

/**
 * TODO: 서버 jar 다운로드 -> serverDirectory에 저장 ->
 *  eula.txt 생성 후 "eula=true"로 설정 -> 프로젝트 .gitignore에 run 파일 추가
 */
abstract class SetupServerTask: DefaultTask() {

    @get:Nested abstract val extension: Property<ServerExtension>

    fun directorySetup(ext: ServerExtension) { // serverDirectory 생성 + .gitignore 추가
        val serverDir = ext.serverDirectory.orNull?.let { project.file(it) } ?: return

        serverDir.mkdirs()

        val gitignore = project.rootDir.resolve(".gitignore")
        if (gitignore.exists()) {
            val content = gitignore.readText()
            if (!content.contains("run/")) {
                gitignore.appendText("\nrun/")
            }
        } else {
            gitignore.writeText("run/")
        }

    }

    private fun downloadServer(ext: ServerExtension) {
        val version = ext.minecraftVersion.get()
        val platform = ext.platform.get()
        val serverDir = project.file(ext.serverDirectory.get())
        val jarFile = serverDir.resolve(platform.name + ".jar")

        if (jarFile.exists()) {
            println("server.jar already exists, skipping download.")
            return
        }

        val url = ServerPlatformResolver().resolve(platform, version)
        download(url, jarFile)
    }

    private fun download(downloadUrl: String, jarFile: java.io.File) {
        URL(downloadUrl).openStream().use { input ->
            jarFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }





    private fun eulaSetup(ext: ServerExtension) {
        if (!ext.acceptEula.get()) return
        val serverDir = project.file(ext.serverDirectory.get())
        serverDir.resolve("eula.txt").writeText("eula=true\n")
    }

    @TaskAction
    fun execute() {
        val ext = extension.get()

        directorySetup(ext)
        downloadServer(ext)
        eulaSetup(ext)
    }

}