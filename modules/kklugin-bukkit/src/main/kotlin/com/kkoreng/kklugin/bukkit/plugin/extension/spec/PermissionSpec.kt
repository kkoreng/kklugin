package com.kkoreng.kklugin.bukkit.plugin.extension.spec

import com.kkoreng.kklugin.bukkit.plugin.extension.enum.PermissionDefault

abstract class PermissionSpec(val name: String) {
    var description: String? = null
    var default: PermissionDefault = PermissionDefault.OP
    var children: Map<String, Boolean> = emptyMap()
}