package kr.kkoreng.kklugin.run.extension.proxy

import kr.kkoreng.kklugin.run.enums.ProxyPlatform
import kr.kkoreng.kklugin.run.extension.server.BackendServerExtension
import kr.kkoreng.kklugin.run.extension.server.ServerBaseExtension
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import javax.inject.Inject

abstract class ProxyServerExtension @Inject constructor(objects: ObjectFactory) : ServerBaseExtension(objects) {

    @get:Input val platform: Property<ProxyPlatform> = objects.property(ProxyPlatform::class.java)

    val serverDirectory: Property<String> = objects.property(String::class.java).convention("run/proxy")

    val backends: NamedDomainObjectContainer<BackendServerExtension> = objects.domainObjectContainer(BackendServerExtension::class.java)

}