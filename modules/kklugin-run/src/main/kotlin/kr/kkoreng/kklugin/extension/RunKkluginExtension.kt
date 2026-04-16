package kr.kkoreng.kklugin.extension

abstract class RunKkluginExtension {

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
 *   port
 *   javaPath
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