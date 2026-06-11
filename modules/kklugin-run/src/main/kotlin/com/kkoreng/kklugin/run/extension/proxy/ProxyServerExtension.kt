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

    // backend 서버가 register될 때마다 포트를 25566부터 자동 증가 할당 + 직접 port를 지정하면 convention 값이 덮어씌워짐
    @get:Nested val backends: NamedDomainObjectContainer<BackendServerExtension> =
        objects.domainObjectContainer(BackendServerExtension::class.java).also { container ->
            val nextPort = java.util.concurrent.atomic.AtomicInteger(25566)
            container.whenObjectAdded { it.port.convention(nextPort.getAndIncrement()) }
        }

}
