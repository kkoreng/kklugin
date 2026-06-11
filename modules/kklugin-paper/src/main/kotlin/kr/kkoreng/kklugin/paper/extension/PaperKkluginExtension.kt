package kr.kkoreng.kklugin.paper.extension

import kr.kkoreng.kklugin.core.extension.KkluginExtension
import kr.kkoreng.kklugin.paper.plugin.extension.PaperPluginExtension
import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

abstract class PaperKkluginExtension @Inject constructor(objects: ObjectFactory) : KkluginExtension(objects) {

    val plugin: PaperPluginExtension = objects.newInstance(PaperPluginExtension::class.java)
    fun plugin(action: Action<PaperPluginExtension>) = action.execute(plugin)

}
