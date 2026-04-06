package kr.kkoreng.kklugin.bukkit.extension.permission

import kr.kkoreng.kklugin.bukkit.extension.enum.PermissionDefault

abstract class PermissionSpec(val name: String) {
    var description: String? = null
    var default: PermissionDefault = PermissionDefault.OP
    var children: Map<String, Boolean> = emptyMap()
}