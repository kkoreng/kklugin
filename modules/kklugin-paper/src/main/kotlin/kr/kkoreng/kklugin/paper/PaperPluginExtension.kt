package kr.kkoreng.kklugin.paper

import kr.kkoreng.kklugin.bukkit.extension.BukkitPluginExtension
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class PaperPluginExtension @Inject constructor(objects: ObjectFactory)  : BukkitPluginExtension(objects) {
    var foliaSupported: Property<Boolean> = objects.property(Boolean::class.java)
    var apiVersion: Property<String> = objects.property(String::class.java)

    var libraries: ListProperty<String> = objects.listProperty(String::class.java)


}