package kr.kkoreng.kklugin.extension.proxy

import kr.kkoreng.kklugin.extension.server.BackendServerExtension
import kr.kkoreng.kklugin.extension.server.ServerBaseExtension
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

abstract class ProxyServerExtension @Inject constructor(objects: ObjectFactory) : ServerBaseExtension(objects) {

    val backends: NamedDomainObjectContainer<BackendServerExtension> =
        objects.domainObjectContainer(BackendServerExtension::class.java)

}