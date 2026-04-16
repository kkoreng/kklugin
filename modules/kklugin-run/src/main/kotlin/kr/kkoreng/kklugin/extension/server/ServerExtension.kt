package kr.kkoreng.kklugin.extension.server

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import javax.inject.Inject

abstract class ServerExtension @Inject constructor(objects: ObjectFactory) : ServerBaseExtension(objects) {

    @get:Input @get:Optional val serverDirectory: Property<String> = objects.property(String::class.java)

}