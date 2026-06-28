package com.kkoreng.kklugin.paper.plugin.tasks

import com.kkoreng.kklugin.core.plugin.tasks.GenerateMetadataTask
import com.kkoreng.kklugin.paper.plugin.extension.PaperPluginExtension
import com.kkoreng.kklugin.paper.plugin.extension.enum.RelativeLoadOrder
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested

abstract class GeneratePaperPluginYamlTask : GenerateMetadataTask() {

    @get:Nested abstract val extension: Property<PaperPluginExtension>

    override fun buildContent(): String {
        val ext = extension.get()
        return buildString {
            appendLine("name: ${ext.name.get()}")
            appendLine("version: ${ext.version.get()}")
            appendLine("main: ${ext.main.get()}")

            appendIfPresent("api-version", ext.apiVersion)
            appendIfPresent("description", ext.description)
            ext.load.orNull?.let { appendLine("load: $it") }

            val authors = ext.authors.get()
            when {
                authors.size == 1 -> appendLine("author: ${authors[0]}")
                authors.size > 1 -> {
                    appendLine("authors:")
                    authors.forEach { appendLine("  - $it") }
                }
            }

            appendList("contributors", ext.contributors.get())
            appendIfPresent("website", ext.website)
            appendList("provides", ext.provides.get())
            appendIfPresent("prefix", ext.prefix)
            ext.foliaSupported.orNull?.let { appendLine("folia-supported: $it") }
            appendIfPresent("bootstrapper", ext.bootStrapper)
            appendIfPresent("loader", ext.loader)

            val serverDeps = ext.serverDependencies
            val bootstrapDeps = ext.bootstrapDependencies
            if (serverDeps.isNotEmpty() || bootstrapDeps.isNotEmpty()) {
                appendLine("dependencies:")
                if (serverDeps.isNotEmpty()) {
                    appendLine("  server:")
                    serverDeps.forEach { dep ->
                        appendLine("    ${dep.name}:")
                        if (dep.load != RelativeLoadOrder.OMIT) appendLine("      load: ${dep.load}")
                        appendLine("      required: ${dep.required}")
                        appendLine("      join-classpath: ${dep.joinClasspath}")
                    }
                }
                if (bootstrapDeps.isNotEmpty()) {
                    appendLine("  bootstrap:")
                    bootstrapDeps.forEach { dep ->
                        appendLine("    ${dep.name}:")
                        if (dep.load != RelativeLoadOrder.OMIT) appendLine("      load: ${dep.load}")
                        appendLine("      required: ${dep.required}")
                        appendLine("      join-classpath: ${dep.joinClasspath}")
                    }
                }
            }

            ext.defaultPermission.orNull?.let {
                appendLine("default-permission: ${it.name.lowercase().replace("_", " ")}")
            }

            if (ext.permissions.isNotEmpty()) {
                appendLine("permissions:")
                ext.permissions.forEach { perm ->
                    appendLine("  ${perm.name}:")
                    perm.description?.let { appendLine("    description: $it") }
                    appendLine("    default: ${perm.default.name.lowercase().replace("_", " ")}")
                    if (perm.children.isNotEmpty()) {
                        appendLine("    children:")
                        perm.children.forEach { (child, value) -> appendLine("      $child: $value") }
                    }
                }
            }
        }
    }

}
