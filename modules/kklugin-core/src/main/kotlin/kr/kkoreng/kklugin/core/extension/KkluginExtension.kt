package kr.kkoreng.kklugin.core.extension

import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

/**
 * The main/top-level extension for Kklugin.
 * This extension will be added to the project when applying the plugin.
 *
 * kklugin {
 *
 *    build {
 *      ...
 *    }
 * }
 */
abstract class KkluginExtension @Inject constructor(objects: ObjectFactory) {
    val build = objects.newInstance(KkluginBuildExtension::class.java)
    fun build(action: Action<KkluginBuildExtension>) = action.execute(build)
}
