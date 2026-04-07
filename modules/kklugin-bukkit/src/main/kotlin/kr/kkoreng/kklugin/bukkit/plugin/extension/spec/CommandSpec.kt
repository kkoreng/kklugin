package kr.kkoreng.kklugin.bukkit.plugin.extension.spec

abstract class CommandSpec(val name: String) {
    var description: String? = null
    var usage: String? = null
    var aliases: List<String> = emptyList()
    var permission: String? = null
}