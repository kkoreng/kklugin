package com.kkoreng.kklugin.run.tasks.run

import com.kkoreng.kklugin.run.extension.server.ServerExtension
import com.kkoreng.kklugin.run.tasks.run.enums.AnsiColor
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction

abstract class RunServerTask : AbstractRunTask() {

    @get:Nested
    abstract val extension: Property<ServerExtension>

    @TaskAction
    fun execute() {
        val ext = extension.get()
        val workingDir = project.file(ext.serverDirectory.get())

        if (!workingDir.resolve(com.kkoreng.kklugin.core.Constants.FileNames.SERVER_JAR).exists()) {
            error("[kklugin] server.jar가 없습니다. 먼저 setupServer를 실행해주세요.")
        }

        val args = buildProgressArgs(
            ext.javaPath.orElse("java").get(),
            ext.jvmArgs.get(),
            workingDir
        )
        val process = startProcess(args, workingDir, "server", AnsiColor.GREEN)
        process.waitFor()
    }
}