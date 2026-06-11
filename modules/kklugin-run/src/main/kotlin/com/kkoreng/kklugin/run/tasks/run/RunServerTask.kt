package com.kkoreng.kklugin.run.tasks.run

import com.kkoreng.kklugin.run.extension.proxy.ProxyServerExtension
import kr.kkoreng.kklugin.run.tasks.run.AbstractRunTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction

abstract class RunServerTask : AbstractRunTask() {

    @get:Nested abstract val extension: Property<ProxyServerExtension>

    @TaskAction
    private fun execute() {

    }
}