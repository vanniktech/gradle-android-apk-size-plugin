package com.vanniktech.android.apk.size

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

class ApkSizeTask extends DefaultTask {
    @Input File apk
    @OutputFile File outputFile

    @TaskAction def sizeApk() {
        final int apkSize = apk.length()

        withStyledOutput { out ->
            final def fileEnding = apk.name[-3..-1].toUpperCase(Locale.US)
            out.warn("Total ${fileEnding} Size in ${apk.name} in bytes: ${apkSize} (${ApkSizeTools.convertBytesToMegaBytes(apkSize)} mb)")
        }

        if (outputFile != null) {
            outputFile.parentFile.mkdirs()
            outputFile.createNewFile()
            outputFile.withOutputStream { stream ->
                def appendableStream = new PrintStream(stream)
                appendableStream.println("bytes,kilobytes,megabytes")

                final String bytes = String.valueOf(apkSize)
                final String kiloBytes = ApkSizeTools.convertBytesToKiloBytes(apkSize)
                final String megaBytes = ApkSizeTools.convertBytesToMegaBytes(apkSize)

                appendableStream.println(bytes + "," + kiloBytes + "," + megaBytes)
            }
        }
    }

    def withStyledOutput(@ClosureParams(value = SimpleType, options = ['org.gradle.api.logging.Logger']) Closure closure) {
        closure(getLogger())
    }
}
