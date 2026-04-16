package kr.kkoreng.kklugin.extension.server

import kr.kkoreng.kklugin.core.extension.KkluginExtension
import kr.kkoreng.kklugin.enums.ServerPlatform
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import javax.inject.Inject

abstract class ServerBaseExtension @Inject constructor(objects: ObjectFactory) : KkluginExtension(objects) {

    @get:Input val minecraftVersion: Property<String> = objects.property(String::class.java)
    @get:Input val platform: Property<ServerPlatform> = objects.property(ServerPlatform::class.java)

    @get:Input @get:Optional val jvmArgs: ListProperty<String> = objects.listProperty(String::class.java)
    @get:Input @get:Optional val javaPath: Property<String> = objects.property(String::class.java)

    @get:Input val port: Property<Int> = objects.property(Int::class.java).convention(25565)
    @get:Input val onlineMode: Property<Boolean> = objects.property(Boolean::class.java).convention(true)
    @get:Input val acceptEula: Property<Boolean> = objects.property(Boolean::class.java).convention(false)

}