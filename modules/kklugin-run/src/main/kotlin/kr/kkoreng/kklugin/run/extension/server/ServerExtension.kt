package com.kkoreng.kklugin.run.extension.server

import com.kkoreng.kklugin.run.enums.ServerPlatform
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import javax.inject.Inject

abstract class ServerExtension @Inject constructor(objects: ObjectFactory) : ServerBaseExtension(objects) {

    @get:Input val serverDirectory: Property<String> = objects.property(String::class.java).convention("run/server")
    @get:Input val platform: Property<ServerPlatform> = objects.property(ServerPlatform::class.java)
    @get:Input val onlineMode: Property<Boolean> = objects.property(Boolean::class.java).convention(true)
    @get:Input val acceptEula: Property<Boolean> = objects.property(Boolean::class.java).convention(false)

}