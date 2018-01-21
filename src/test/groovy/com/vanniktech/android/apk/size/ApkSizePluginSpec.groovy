package com.vanniktech.android.apk.size

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification
import spock.lang.Unroll

final class ApkSizePluginSpec extends Specification {
    @Rule final TemporaryFolder tempFolder = new TemporaryFolder()
    final static COMPILE_SDK_VERSION = 27
    final static BUILD_TOOLS_VERSION = "27.0.3"
    final static APPLICATION_ID = "com.example"
    final static MANIFEST_FILE_TEXT = """<?xml version="1.0" encoding="utf-8"?>
        <manifest package="com.getkeepsafe.dexcount.integration"
                  xmlns:android="http://schemas.android.com/apk/res/android">
            <application/>
        </manifest>
    """

    Project project

    def "setup"() {
        project = ProjectBuilder.builder().build()
        def manifestFile = new File(project.projectDir, "src/main/AndroidManifest.xml")
        manifestFile.parentFile.mkdirs()
        manifestFile.write(MANIFEST_FILE_TEXT)
    }

    def "project is invalid"() {
        when:
        new ApkSizePlugin().apply(project) // apply plugin: "com.vanniktech.android.apk.size"

        then:
        def e = thrown UnsupportedOperationException
        e.message == "APK Size Plugin requires the Android Application or Library plugin to be configured"
    }

    @Unroll "#projectPlugin project applied correctly"() {
        given:
        project.apply plugin: projectPlugin

        when:
        project.apply plugin: "com.vanniktech.android.apk.size"

        then:
        noExceptionThrown()

        where:
        projectPlugin << ["com.android.application", "com.android.library"]
    }

    def "android - all tasks created"() {
        given:
        project.apply plugin: "com.android.application"
        project.apply plugin: "com.vanniktech.android.apk.size"
        project.android {
          compileSdkVersion COMPILE_SDK_VERSION
          buildToolsVersion BUILD_TOOLS_VERSION

          defaultConfig {
            applicationId APPLICATION_ID
          }
        }

        when:
        project.evaluate()

        then:
        project.tasks.getByName("sizeDebugDebugApk")
        project.tasks.getByName("sizeReleaseReleaseApk")
    }
}
