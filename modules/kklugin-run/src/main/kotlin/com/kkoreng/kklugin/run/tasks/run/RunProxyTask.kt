package com.kkoreng.kklugin.run.tasks.run

import com.kkoreng.kklugin.run.extension.proxy.ProxyServerExtension
import com.kkoreng.kklugin.run.tasks.run.enums.AnsiColor
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction

/**
 * runProxy 실행 시 dependOn으로 setupProxy 자동 선행
 * 색상 + prefix로 터미널 내부 backend 서버 구분
 */
abstract class RunProxyTask : AbstractRunTask() {

    @get:Nested abstract val extension: Property<ProxyServerExtension>

    @TaskAction
    fun execute() {
        val ext = extension.get()
        val colors = AnsiColor.entries.filter { it != AnsiColor.CYAN && it != AnsiColor.RESET }
        val processes = mutableListOf<Process>()

        // 프록시 실행
        val proxyDir = project.file(ext.serverDirectory.get())
        if (!proxyDir.resolve(com.kkoreng.kklugin.core.Constants.FileNames.SERVER_JAR).exists()) {
            error("[kklugin] proxy server.jar가 없습니다. 먼저 setupProxy를 실행해주세요.")
        }
        val proxyArgs = buildProgressArgs(
            ext.javaPath.orElse("java").get(),
            ext.jvmArgs.get(),
            proxyDir
        )
        processes.add(startProcess(proxyArgs, proxyDir, "proxy", AnsiColor.CYAN))

        // 백엔드 서버들 실행
        ext.backends.forEachIndexed { index, backend ->
            val workingDir = project.file(backend.serverDirectory.get())
            if (!workingDir.resolve(com.kkoreng.kklugin.core.Constants.FileNames.SERVER_JAR).exists()) {
                error("[kklugin] ${backend.getName()} server.jar가 없습니다. 먼저 setupProxy를 실행해주세요.")
            }
            val args = buildProgressArgs(
                backend.javaPath.orElse("java").get(),
                backend.jvmArgs.get(),
                workingDir
            )
            processes.add(startProcess(args, workingDir, backend.getName(), colors[index % colors.size]))
        }

        processes.forEach { it.waitFor() }
    }
}
