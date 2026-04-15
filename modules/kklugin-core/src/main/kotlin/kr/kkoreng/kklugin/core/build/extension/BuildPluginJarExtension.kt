package kr.kkoreng.kklugin.core.build.extension

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import javax.inject.Inject

abstract class BuildPluginJarExtension @Inject constructor(objects: ObjectFactory){

    /**
     * Whether to create a shadow jar or not.
     * Default is false.
     */
    @get:Input val shadow: Property<Boolean> = objects.property(Boolean::class.java).convention(false)

    @get:Input @get:Optional val outputDirectory: Property<String> = objects.property(String::class.java)

    @get:Input val minimize: Property<Boolean> = objects.property(Boolean::class.java).convention(false)

    @get:Input val relocations: MapProperty<String, String> = objects.mapProperty(String::class.java, String::class.java)

    @get:Input val exclude: ListProperty<String> = objects.listProperty(String::class.java)

}