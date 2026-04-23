package com.kkoreng.kklugin.run.extension.proxy

import com.kkoreng.kklugin.run.enums.ProxyPlatform
import com.kkoreng.kklugin.run.extension.server.BackendServerExtension
import com.kkoreng.kklugin.run.extension.server.ServerBaseExtension
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.Optional
import javax.inject.Inject

abstract class ProxyServerExtension @Inject constructor(objects: ObjectFactory) : ServerBaseExtension(objects) {

    @get:Input val platform: Property<ProxyPlatform> = objects.property(ProxyPlatform::class.java)

    @get:Input val serverDirectory: Property<String> = objects.property(String::class.java).convention("run/proxy")

    @get:Input @get:Optional val defaultServer: Property<String> = objects.property(String::class.java)

    @get:Nested val backends: NamedDomainObjectContainer<BackendServerExtension> = objects.domainObjectContainer(BackendServerExtension::class.java)

}
