package kr.kkoreng.kklugin.run.tasks.run

import kr.kkoreng.kklugin.run.tasks.run.enums.AnsiColor
import org.gradle.api.DefaultTask
import java.io.File

abstract class AbstractRunTask : DefaultTask() {


    protected fun buildProgressArgs(
        javaPath: String,
        jvmArgs: List<String>,
        workingDir: File
    ): List<String> {

    }


    // run 프로세스 실행
    protected fun startProcess(
        args: List<String>,
        workingDir: File,
        label: String,
        color: AnsiColor
    ): Process {

    }


}