package kr.kkoreng.kklugin.bukkit.extension

import kr.kkoreng.kklugin.bukkit.extension.command.CommandSpec
import kr.kkoreng.kklugin.bukkit.extension.enum.LoadType
import kr.kkoreng.kklugin.bukkit.extension.permission.PermissionSpec
import kr.kkoreng.kklugin.core.PluginMetadata
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class BukkitPluginExtension @Inject constructor(objects: ObjectFactory) : PluginMetadata {

    override var name: Property<String> = objects.property(String::class.java).convention("MyPlugin")
    override var version: Property<String> = objects.property(String::class.java).convention("1.0.0-SNAPSHOT")
    override var main: Property<String> = objects.property(String::class.java).convention("com.example.MyPlugin")

    override var description: Property<String> = objects.property(String::class.java)
    override var authors: ListProperty<String> = objects.listProperty(String::class.java)
    override var website: Property<String> = objects.property(String::class.java)

    var load: Property<LoadType> = objects.property(LoadType::class.java)


    var depend: ListProperty<String> = objects.listProperty(String::class.java)
    var softDepend: ListProperty<String> = objects.listProperty(String::class.java)
    var loadBefore: ListProperty<String> = objects.listProperty(String::class.java)

    val commands: NamedDomainObjectContainer<CommandSpec> = objects.domainObjectContainer(CommandSpec::class.java)
    val permissions: NamedDomainObjectContainer<PermissionSpec> = objects.domainObjectContainer(PermissionSpec::class.java)

}