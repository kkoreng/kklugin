package kr.kkoreng.kklugin.core

import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

interface PluginMetadata {
    // 필수
    var name: Property<String>
    var version: Property<String>
    var main: Property<String>

    // 선택
    var description: Property<String>
    var authors: ListProperty<String>
    var website: Property<String>
}