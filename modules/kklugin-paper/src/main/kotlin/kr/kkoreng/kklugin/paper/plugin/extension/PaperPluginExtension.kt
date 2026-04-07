package kr.kkoreng.kklugin.paper.plugin.extension

import kr.kkoreng.kklugin.bukkit.plugin.extension.BasePluginExtension
import kr.kkoreng.kklugin.paper.plugin.extension.spec.PaperDependencySpec
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import javax.inject.Inject

abstract class PaperPluginExtension @Inject constructor(objects: ObjectFactory) : BasePluginExtension(objects) {

    @get:Input @get:Optional val bootstrapper: Property<String> = objects.property(String::class.java)
    @get:Input @get:Optional val loader: Property<String> = objects.property(String::class.java)

    @get:Internal val dependencies: NamedDomainObjectContainer<PaperDependencySpec> =
        objects.domainObjectContainer(PaperDependencySpec::class.java)

}
