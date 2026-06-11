package com.kkoreng.kklugin.core.extension

import com.kkoreng.kklugin.core.build.extension.BuildPluginJarExtension
import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

abstract class KkluginExtension @Inject constructor(objects: ObjectFactory) {
    val build = objects.newInstance(BuildPluginJarExtension::class.java)
    fun build(action: Action<BuildPluginJarExtension>) = action.execute(build)
}