package com.kkoreng.kklugin.run.tasks.run

import com.kkoreng.kklugin.core.Constants
import com.kkoreng.kklugin.run.tasks.run.enums.AnsiColor
import org.gradle.api.DefaultTask
import java.io.File

abstract class AbstractRunTask : DefaultTask() {

    /**
     * ["java, "-Xmx2G", "Xms2G", "-jar", "/path/server.jar"] 리스트 반환
     */
    protected fun buildProgressArgs(
        javaPath: String,
        jvmArgs: List<String>,
        workingDir: File
    ): List<String> = buildList {
        add(javaPath)
        addAll(jvmArgs)
        add("-jar")
        add(File(workingDir, Constants.FileNames.SERVER_JAR).absolutePath)
    }

    protected fun startProcess(
        args: List<String>,
        workingDir: File,
        label: String,
        color: AnsiColor
    ): Process {
        val process = ProcessBuilder(args)
            .directory(workingDir)
            .redirectErrorStream(true)
            .start()

        Thread {
            process.inputStream.bufferedReader().forEachLine { line ->
                System.out.write("${color.value}[$label]${AnsiColor.RESET.value} $line\n".toByteArray())
                System.out.flush()
            }
        }.apply {
            isDaemon = true
            start()
        }

        return process
    }

}
