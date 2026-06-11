package com.kkoreng.kklugin.run.extension

import com.kkoreng.kklugin.core.extension.KkluginExtension
import com.kkoreng.kklugin.run.extension.proxy.ProxyServerExtension
import com.kkoreng.kklugin.run.extension.server.ServerExtension
import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

abstract class RunKkluginExtension @Inject constructor(objects: ObjectFactory) : KkluginExtension(objects) {

    val server: ServerExtension = objects.newInstance(ServerExtension::class.java)
    fun runServer(action: Action<ServerExtension>) = action.execute(server)

    val proxy: ProxyServerExtension = objects.newInstance(ProxyServerExtension::class.java)
    fun runProxy(action: Action<ProxyServerExtension>) = action.execute(proxy)

}

/**
 * 단일 서버
 * - minecraftVersion
 * - platform: ServerPlatform
 * - serverDirectory
 * - jvmArgs
 * - javaPath
 * - port
 * - onlineMode
 * - acceptEula
 */
/** Proxy 서버
 * runProxy {
 *   minecraftVersion
 *   platform: ProxyPlatform
 *   serverDirectory
 *   jvmArgs
 *   javaPath
 *   port
 *
 *   backends("lobby") {
 *      minecraftVersion
 *      platform: ServerPlatform
 *      jvmArgs
 *      javaPath
 *      port
 *      acceptEula
 *   }
 *
 *   backends("arena") {
 *      minecraftVersion
 *      platform
 *      jvmArgs
 *      javaPath
 *      port
 *      acceptEula
 *   }
 *
 * }
 */