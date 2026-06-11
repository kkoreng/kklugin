package com.kkoreng.kklugin.run.tasks.run

import com.kkoreng.kklugin.run.extension.server.ServerExtension
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction

/**
 * runProxy 실행 시 dependOn으로 setupProxy 자동 선행
 * 색상 + prefix로 터미널 내부 backend 서버 구분
 */
abstract class RunProxyTask: AbstractRunTask() {

    @get:Nested abstract val extension: Property<ServerExtension>

    @TaskAction
    private fun execute() {

    }
}