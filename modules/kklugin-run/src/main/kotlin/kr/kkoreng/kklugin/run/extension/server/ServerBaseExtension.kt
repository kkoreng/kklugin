package com.kkoreng.kklugin.run.extension.server

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import javax.inject.Inject

abstract class ServerBaseExtension @Inject constructor(objects: ObjectFactory) {

    @get:Input val minecraftVersion: Property<String> = objects.property(String::class.java)
    @get:Input @get:Optional val buildVersion: Property<String> = objects.property(String::class.java)

    @get:Input @get:Optional val javaPath: Property<String> = objects.property(String::class.java)
    @get:Input @get:Optional val jvmArgs: ListProperty<String> = objects.listProperty(String::class.java)

    @get:Input val port: Property<Int> = objects.property(Int::class.java).convention(25565)

}