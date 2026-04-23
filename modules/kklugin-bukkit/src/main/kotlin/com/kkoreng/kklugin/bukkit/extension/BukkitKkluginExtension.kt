package com.kkoreng.kklugin.bukkit.extension

import com.kkoreng.kklugin.bukkit.plugin.extension.PluginExtension
import com.kkoreng.kklugin.core.extension.KkluginExtension
import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

abstract class BukkitKkluginExtension @Inject constructor(objects: ObjectFactory) : KkluginExtension(objects) {

    val plugin: PluginExtension = objects.newInstance(PluginExtension::class.java)
    fun plugin(action: Action<PluginExtension>) = action.execute(plugin)

}
