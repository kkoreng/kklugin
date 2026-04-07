package kr.kkoreng.kklugin.paper.plugin.tasks

import kr.kkoreng.kklugin.core.tasks.GenerateMetadataTask
import kr.kkoreng.kklugin.paper.plugin.extension.PaperPluginExtension
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

            ext.apiVersion.orNull?.let { appendLine("api-version: $it") }
            ext.description.orNull?.let { if (it.isNotEmpty()) appendLine("description: $it") }
            ext.prefix.orNull?.let { if (it.isNotEmpty()) appendLine("prefix: $it") }
            ext.website.orNull?.let { if (it.isNotEmpty()) appendLine("website: $it") }

            val authors = ext.authors.get()
            when {
                authors.size == 1 -> appendLine("author: ${authors[0]}")
                authors.size > 1 -> {
                    appendLine("authors:")
                    authors.forEach { appendLine("  - $it") }
                }
            }

            appendList("contributors", ext.contributors.get())
            appendList("libraries", ext.libraries.get())

            ext.foliaSupported.orNull?.let { appendLine("folia-supported: $it") }

            ext.bootstrapper.orNull?.let { if (it.isNotEmpty()) appendLine("bootstrapper: $it") }
            ext.loader.orNull?.let { if (it.isNotEmpty()) appendLine("loader: $it") }

            if (ext.dependencies.isNotEmpty()) {
                appendLine("dependencies:")
                ext.dependencies.forEach { dep ->
                    appendLine("  ${dep.name}:")
                    appendLine("    load: ${dep.load}")
                    appendLine("    required: ${dep.required}")
                    appendLine("    join-classpath: ${dep.joinClasspath}")
                }
            }
        }
    }

    private fun StringBuilder.appendList(key: String, list: List<String>) {
        if (list.isNotEmpty()) {
            appendLine("$key:")
            list.forEach { appendLine("  - $it") }
        }
    }
}
