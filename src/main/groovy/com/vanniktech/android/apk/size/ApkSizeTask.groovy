package com.vanniktech.android.apk.size

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

class ApkSizeTask extends DefaultTask {
    @Input File apk
    @OutputFile File outputFile
    ApkSizeExtension extension

    @TaskAction sizeApk() {
        final int apkSize = apk.length()

        withStyledOutput { out ->
            final fileEnding = apk.name[-3..-1].toUpperCase(Locale.US)
            out.warn("Total ${fileEnding} Size in ${apk.name} in bytes: ${apkSize} (${ApkSizeTools.convertBytesToMegaBytes(apkSize)} mb)")
        }

        if (outputFile != null) {
            outputFile.parentFile.mkdirs()
            outputFile.createNewFile()
            outputFile.withOutputStream { stream ->
                final appendableStream = new PrintStream(stream)
                appendableStream.println("bytes,kilobytes,megabytes")

                final String bytes = String.valueOf(apkSize)
                final String kiloBytes = ApkSizeTools.convertBytesToKiloBytes(apkSize)
                final String megaBytes = ApkSizeTools.convertBytesToMegaBytes(apkSize)

                appendableStream.println(bytes + "," + kiloBytes + "," + megaBytes)
            }
        }

        failBuildMaxMethods(apkSize, extension.maxApkSize)
    }

    def withStyledOutput(@ClosureParams(value = SimpleType, options = ["org.gradle.api.logging.Logger"]) Closure closure) {
        closure(getLogger())
    }

    def failBuildMaxMethods(apkSize, maxApkSize) {
        if (extension.maxApkSize > 0 && apkSize > extension.maxApkSize) {
            throw new GradleException(String.format("The current APK is %d Kb, the current max is: %d Kb.", apkSize, maxApkSize))
        }
    }
}
