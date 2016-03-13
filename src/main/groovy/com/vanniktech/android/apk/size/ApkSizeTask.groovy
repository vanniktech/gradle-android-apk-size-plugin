package com.vanniktech.android.apk.size

import org.gradle.api.DefaultTask
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.logging.StyledTextOutput
import org.gradle.logging.StyledTextOutputFactory

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType

class ApkSizeTask extends DefaultTask {
    @Input def File apk
    @OutputFile def File outputFile

    @TaskAction
    void sizeApk() {
        final int apkSize = apk.length()

        withStyledOutput(StyledTextOutput.Style.Info) { out ->
            def fileEnding = apk.name[-3..-1].toUpperCase(Locale.US)
            out.println("Total ${fileEnding} Size in ${apk.name} in bytes: ${apkSize}")
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

    private void withStyledOutput(StyledTextOutput.Style style, LogLevel level = null, @ClosureParams(value = SimpleType, options = ['org.gradle.logging.StyledTextOutput']) Closure closure) {
        def factory = services.get(StyledTextOutputFactory)
        def output = level == null ? factory.create('apksize') : factory.create('apksize', level)

        closure(output.withStyle(style))
    }
}
