package kr.kkoreng.kklugin.paper.plugin.extension.spec

import kr.kkoreng.kklugin.paper.plugin.extension.enum.RelativeLoadOrder

abstract class PaperDependencySpec(val name: String) {
    var load: RelativeLoadOrder = RelativeLoadOrder.OMIT
    var required: Boolean = true
    var joinClasspath: Boolean = true
}