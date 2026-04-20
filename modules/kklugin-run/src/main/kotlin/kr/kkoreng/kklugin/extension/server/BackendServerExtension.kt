package kr.kkoreng.kklugin.extension.server

import kr.kkoreng.kklugin.enums.ServerPlatform
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import javax.inject.Inject

abstract class BackendServerExtension @Inject constructor(
    private val name: String,
    objects: ObjectFactory
) : ServerBaseExtension(objects) {

    @get:Input val serverDirectory: Property<String> = objects.property(String::class.java).convention("run/backends/$name")
    @get:Input val platform: Property<ServerPlatform> = objects.property(ServerPlatform::class.java)
    @get:Input val onlineMode: Property<Boolean> = objects.property(Boolean::class.java).convention(true)
    @get:Input val acceptEula: Property<Boolean> = objects.property(Boolean::class.java).convention(false)

    fun getName(): String = name

}