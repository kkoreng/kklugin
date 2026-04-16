package kr.kkoreng.kklugin.extension.server

import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

abstract class BackendServerExtension @Inject constructor(
    private val name: String,
    objects: ObjectFactory
) : ServerBaseExtension(objects) {

    fun getName(): String = name

}