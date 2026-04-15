package kr.kkoreng.kklugin.paper.plugin.extension

import kr.kkoreng.kklugin.bukkit.plugin.extension.enum.LoadType
import kr.kkoreng.kklugin.bukkit.plugin.extension.enum.PermissionDefault
import kr.kkoreng.kklugin.bukkit.plugin.extension.spec.PermissionSpec
import kr.kkoreng.kklugin.paper.plugin.extension.spec.PaperDependencySpec
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import javax.inject.Inject

abstract class PaperPluginExtension @Inject constructor(objects: ObjectFactory) {

    @get:Input @get:Optional val minecraftVersion: Property<String> = objects.property(String::class.java)

    @get:Input val name: Property<String> = objects.property(String::class.java).convention("MyPlugin")
    @get:Input val version: Property<String> = objects.property(String::class.java).convention("1.0.0-SNAPSHOT")
    @get:Input val main: Property<String> = objects.property(String::class.java).convention("com.example.MyPlugin")

    @get:Input @get:Optional val apiVersion: Property<String> = objects.property(String::class.java)
    @get:Input @get:Optional val description: Property<String> = objects.property(String::class.java)
    @get:Input @get:Optional val load: Property<LoadType> = objects.property(LoadType::class.java)
    @get:Input @get:Optional val authors: ListProperty<String> = objects.listProperty(String::class.java)
    @get:Input @get:Optional val contributors: ListProperty<String> = objects.listProperty(String::class.java)
    @get:Input @get:Optional val website: Property<String> = objects.property(String::class.java)

    @get:Input @get:Optional val provides: ListProperty<String> = objects.listProperty(String::class.java)
    @get:Input @get:Optional val prefix: Property<String> = objects.property(String::class.java)
    @get:Input @get:Optional val foliaSupported: Property<Boolean> = objects.property(Boolean::class.java)

    @get:Input @get:Optional val defaultPermission: Property<PermissionDefault> = objects.property(PermissionDefault::class.java)
    @get:Internal val permissions: NamedDomainObjectContainer<PermissionSpec> = objects.domainObjectContainer(PermissionSpec::class.java)

    @get:Input @get:Optional val bootStrapper: Property<String> = objects.property(String::class.java)
    @get:Input @get:Optional val loader: Property<String> = objects.property(String::class.java)

    @get:Internal val serverDependencies: NamedDomainObjectContainer<PaperDependencySpec> = objects.domainObjectContainer(PaperDependencySpec::class.java)
    @get:Internal val bootstrapDependencies: NamedDomainObjectContainer<PaperDependencySpec> = objects.domainObjectContainer(PaperDependencySpec::class.java)

}
