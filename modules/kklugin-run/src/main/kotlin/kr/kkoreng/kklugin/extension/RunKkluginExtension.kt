package kr.kkoreng.kklugin.extension

import kr.kkoreng.kklugin.core.extension.KkluginExtension
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

abstract class RunKkluginExtension @Inject constructor(objects: ObjectFactory): KkluginExtension(objects) {


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
 *      onlineMode
 *      acceptEula
 *   }
 *
 *   backends("arena") {
 *      minecraftVersion
 *      platform
 *      jvmArgs
 *      javaPath
 *      port
 *      onlineMode
 *      acceptEula
 *   }
 *
 * }
 */