package kr.kkoreng.kklugin.core.extension

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class KkluginBuildExtension @Inject constructor(objects: ObjectFactory){

    /**
     * Whether to create a shadow jar or not.
     * Default is false.
     */
    val shadow: Property<Boolean> = objects.property(Boolean::class.java).convention(false)

    /**
     * Whether to minimize the shadow jar by removing unused classes
     * Default is false.
     */
    val minimize: Property<Boolean> = objects.property(Boolean::class.java).convention(false)

    /**
     * Relocations to apply to the shadow jar.
     * Package relocation rules.
     * Key is the original package path, value is the relocated path.
     */
    val relocations: MapProperty<String, String> = objects.mapProperty(String::class.java, String::class.java)

    /**
     * List of file patterns to exclude from the shadow jar.
     */
    val exclude: ListProperty<String> = objects.listProperty(String::class.java)

}