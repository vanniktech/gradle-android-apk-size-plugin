package com.vanniktech.android.apk.size

import com.android.build.gradle.api.BaseVariantOutput
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

class ApkSizeTask extends DefaultTask {
    @Input
    def BaseVariantOutput apk

    @OutputFile
    def File outputFile

    @TaskAction
    void sizeApk() {
        if (outputFile != null) {
            outputFile.parentFile.mkdirs()
            outputFile.createNewFile()
            outputFile.withOutputStream { stream ->
                def appendableStream = new PrintStream(stream)
                appendableStream.println("bytes,kilobytes,megabytes")

                final int size = apk.outputFile.length()

                final String bytes = String.valueOf(size)
                final String kiloBytes = ApkSizeTools.convertBytesToKiloBytes(size)
                final String megaBytes = ApkSizeTools.convertBytesToMegaBytes(size)

                appendableStream.println(bytes + "," + kiloBytes + "," + megaBytes)
            }
        }
    }
}
