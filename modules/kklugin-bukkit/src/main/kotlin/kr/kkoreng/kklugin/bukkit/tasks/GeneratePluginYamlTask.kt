package kr.kkoreng.kklugin.bukkit.tasks

import kr.kkoreng.kklugin.bukkit.extension.BukkitPluginExtension
import kr.kkoreng.kklugin.core.GenerateMetadataTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested

abstract class GeneratePluginYamlTask : GenerateMetadataTask() {

    @get:Nested
    abstract val extension: Property<BukkitPluginExtension>

    override fun generate() {
        val ext = extension.get()

        val pluginYamlContent = buildString {
            appendLine("name: ${ext.name.get()}")
            appendLine("version: ${ext.version.get()}")
            appendLine("main: ${ext.main.get()}")

            appendIfPresent("description", ext.description)
            appendIfPresent("website", ext.website)

            val authors = ext.authors.get()
            when {
                authors.size == 1 -> appendLine("author: ${authors[0]}")
                authors.size > 1 -> {
                    appendLine("authors:")
                    authors.forEach { appendLine("  - $it") }
                }
            }

            ext.load.orNull?.let { appendLine("load: $it") }

            appendList("depend", ext.depend.get())
            appendList("softdepend", ext.softDepend.get())
            appendList("loadbefore", ext.loadBefore.get())

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

        outputFile.get().asFile.writeText(pluginYamlContent)
    }

    private fun StringBuilder.appendIfPresent(key: String, property: Property<String>) {
        property.orNull?.let { if (it.isNotEmpty()) appendLine("$key: $it") }
    }

    private fun StringBuilder.appendList(key: String, list: List<String>) {
        if (list.isNotEmpty()) {
            appendLine("$key:")
            list.forEach { appendLine("  - $it") }
        }
    }
}
