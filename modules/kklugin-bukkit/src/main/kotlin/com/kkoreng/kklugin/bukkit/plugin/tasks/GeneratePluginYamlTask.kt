package com.kkoreng.kklugin.bukkit.plugin.tasks

import com.kkoreng.kklugin.bukkit.plugin.extension.PluginExtension
import com.kkoreng.kklugin.core.plugin.tasks.GenerateMetadataTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested

abstract class GeneratePluginYamlTask : GenerateMetadataTask() {

    @get:Nested abstract val extension: Property<PluginExtension>

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

            appendList("depend", ext.depend.get())
            appendList("softdepend", ext.softDepend.get())
            appendList("loadbefore", ext.loadBefore.get())
            appendList("provides", ext.provides.get())

            appendIfPresent("prefix", ext.prefix)
            appendList("libraries", ext.libraries.get())
            ext.foliaSupported.orNull?.let { appendLine("folia-supported: $it") }
            appendIfPresent("paperPluginLoader", ext.paperPluginLoader)
            ext.paperSkipLibraries.orNull?.let { appendLine("paper-skip-libraries: $it") }

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

            if (ext.commands.isNotEmpty()) {
                appendLine("commands:")
                ext.commands.forEach { cmd ->
                    appendLine("  ${cmd.name}:")
                    cmd.description?.let { appendLine("    description: $it") }
                    cmd.usage?.let { appendLine("    usage: $it") }
                    if (cmd.aliases.isNotEmpty()) appendLine("    aliases: ${cmd.aliases}")
                    cmd.permission?.let { appendLine("    permission: $it") }
                }
            }
        }
    }
}