package com.kkoreng.kklugin.run.tasks.setup

import com.kkoreng.kklugin.run.extension.proxy.ProxyServerExtension
import com.kkoreng.kklugin.run.extension.server.BackendServerExtension
import com.kkoreng.kklugin.run.platform.resolver.ProxyPlatformResolver
import com.kkoreng.kklugin.run.platform.resolver.ServerPlatformResolver
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * setupProxy -> setupServer와 같은 로직 실행(directorySetup, downloadServer)
 *              -> velocity.toml 생성 -> player-info-forwarding-mode = "modern" + backend extension 기반 servers 설정
 *              -> forwarding.secret 자동 생성
 *              -> 각 백엔드 서버에 대해 server.jar 다운로드 -> 각 백엔드 서버 eulaSetup 실행 + server.properties 설정 + paper-global secret 설정
 */
abstract class SetupProxyTask : AbstractSetupTask() {

    @get:Nested abstract val extension: Property<ProxyServerExtension>

    private fun downloadProxy(ext: ProxyServerExtension, proxyDir: File) {
        val jarFile = proxyDir.resolve("server.jar")
        if (jarFile.exists()) {
            println("proxy/server.jar already exists, skipping download.")
            return
        }
        val version = ext.buildVersion.orNull ?: ext.minecraftVersion.get()
        val url = ProxyPlatformResolver().resolve(ext.platform.get(), version, "latest")
        download(url, jarFile)
    }

    private fun generateForwardingSecret(proxyDir: File): String {
        val secretFile = proxyDir.resolve("forwarding.secret")
        if (secretFile.exists()) return secretFile.readText().trim()
        val secret = java.util.UUID.randomUUID().toString()
        secretFile.writeText(secret)
        return secret
    }

    private fun generateVelocityToml(ext: ProxyServerExtension, proxyDir: File) {
        val tomlFile = proxyDir.resolve("velocity.toml")
        val serversBlock = ext.backends.joinToString("\n") { backend ->
            "${backend.getName()} = \"localhost:${backend.port.get()}\""
        }

        // try = ["lobby"] 에 들어갈 서버 - defaultServer 지정 시 우선, 없으면 첫 번째 백엔드
        val defaultServer = ext.defaultServer.orNull
            ?: ext.backends.firstOrNull()?.getName()
            ?: ""

        if (!tomlFile.exists()) {
            val template = javaClass.getResourceAsStream("/templates/velocity.toml.template")
                ?.bufferedReader()?.readText() ?: error("velocity.toml template not found")

            tomlFile.writeText(
                template
                    .replace("{servers}", serversBlock)
                    .replace("{defaultServer}", defaultServer)
            )
            return
        }

        // 이미 존재하면 [servers] 섹션에 없는 항목만 추가
        var content = tomlFile.readText()
        ext.backends.forEach { backend ->
            val entry = "${backend.getName()} = \"localhost:${backend.port.get()}\""
            if (!content.contains("${backend.getName()} =")) {
                content = content.replace("[servers]", "[servers]\n$entry")
            }
        }
        tomlFile.writeText(content)
    }

    private fun setupBackend(backend: BackendServerExtension, secret: String) {
        val backendDir = project.file(backend.serverDirectory.get())
        backendDir.mkdirs()

        // server.jar 다운로드
        val jarFile = backendDir.resolve("server.jar")
        if (jarFile.exists()) {
            println("${backend.getName()}/server.jar already exists, skipping download.")
        } else {
            val url = ServerPlatformResolver().resolve(backend.platform.get(), backend.minecraftVersion.get(), "latest")
            download(url, jarFile)
        }

        // eula.txt
        eulaSetup(backend.acceptEula.get(), backendDir)

        // server.properties
        val propertiesFile = backendDir.resolve("server.properties")
        if (!propertiesFile.exists()) {
            propertiesFile.writeText("""
                online-mode=false
                server-port=${backend.port.get()}
            """.trimIndent())
        }

        // paper-global.yml (Paper, Folia, Purpur 공통)
        val configDir = backendDir.resolve("config")
        configDir.mkdirs()
        val paperGlobal = configDir.resolve("paper-global.yml")
        if (!paperGlobal.exists()) {
            paperGlobal.writeText("""
                proxies:
                  velocity:
                    enabled: true
                    online-mode: true
                    secret: "$secret"
            """.trimIndent())
        }
    }

    @TaskAction
    fun execute() {
        val ext = extension.get()
        val proxyDir = ext.serverDirectory.orNull?.let { project.file(it) } ?: return

        directorySetup(proxyDir)
        downloadProxy(ext, proxyDir)
        val secret = generateForwardingSecret(proxyDir)
        generateVelocityToml(ext, proxyDir)

        ext.backends.forEach { backend ->
            setupBackend(backend, secret)
        }
    }
}