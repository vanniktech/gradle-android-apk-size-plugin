package com.vanniktech.android.apk.size

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

final class ApkSizeTaskSpec extends Specification {
    @Rule final TemporaryFolder tempFolder = new TemporaryFolder()
    Project project
    File apkFile
    File outputFile

    def "setup"() {
        project = ProjectBuilder.builder().build()

        apkFile = tempFolder.newFile("tiles.apk")
        def apkResource = getClass().getResourceAsStream("/tiles.apk")
        apkResource.withStream { input ->
          apkFile.append(input)
        }

        outputFile = tempFolder.newFile("test-result.csv")
        def outputResource = getClass().getResourceAsStream("/test-result.csv")
        outputResource.withStream { input ->
          outputFile.append(input)
        }
    }

    def "can add task to project"() {
        expect:
        project.task("apkSize", type: ApkSizeTask) instanceof ApkSizeTask
    }

    def "writes output file"() {
        given:
        def task = project.task("apkSize", type: ApkSizeTask) as ApkSizeTask
        task.apk = apkFile
        task.outputFile = outputFile
        task.extension = new ApkSizeExtension(maxApkSize: 2000000)

        when:
        task.execute()

        then:
        def actual = task.outputFile.text
        def expected =
          """bytes,kilobytes,megabytes
1899702,1899.7,1.9
"""
        actual == expected
    }
}
