package com.vanniktech.android.apk.size

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.io.PrintStream
import java.util.Locale

open class ApkSizeTask : DefaultTask() {
  @Input lateinit var apk: File
  @OutputFile lateinit var outputFile: File
  lateinit var extension: ApkSizeExtension

  @TaskAction fun sizeApk() {
    val apkSize = apk.length()

    val fileEnding = apk.extension.toUpperCase(Locale.US)
    logger.log(LogLevel.LIFECYCLE, "Total $fileEnding Size in ${apk.name} in bytes: $apkSize (${ApkSizeTools.convertBytesToMegaBytes(apkSize)} mb)")
    if (extension.teamcity) {
      logger.log(LogLevel.LIFECYCLE, "##teamcity[buildStatisticValue key='ApkSize_${apk.name}_B' value='$apkSize']")
      logger.log(LogLevel.LIFECYCLE, "##teamcity[buildStatisticValue key='ApkSize_${apk.name}_MB' value='${ApkSizeTools.convertBytesToMegaBytes(apkSize)}']")
    }

    outputFile.parentFile.mkdirs()
    outputFile.createNewFile()
    outputFile.outputStream().use { outputStream ->
      val appendableStream = PrintStream(outputStream)
      appendableStream.println("bytes,kilobytes,megabytes")

      val bytes = apkSize.toString()
      val kiloBytes = ApkSizeTools.convertBytesToKiloBytes(apkSize)
      val megaBytes = ApkSizeTools.convertBytesToMegaBytes(apkSize)

      appendableStream.println("$bytes,$kiloBytes,$megaBytes")
    }

    failBuildMaxMethods(apkSize, extension.maxApkSize)
  }

  private fun failBuildMaxMethods(apkSize: Long, maxApkSize: Int) {
    if (extension.maxApkSize in 1 until apkSize) {
      throw GradleException("The current APK is $apkSize Kb, the current max is: $maxApkSize Kb.")
    }
  }
}
