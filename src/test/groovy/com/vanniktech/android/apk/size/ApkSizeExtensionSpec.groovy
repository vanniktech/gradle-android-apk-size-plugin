package com.vanniktech.android.apk.size

import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

final class ApkSizeExtensionSpec extends Specification {
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
  File apkFile

  def "setup"() {
    project = ProjectBuilder.builder().build()
    def manifestFile = new File(project.projectDir, "src/main/AndroidManifest.xml")
    manifestFile.parentFile.mkdirs()
    manifestFile.write(MANIFEST_FILE_TEXT)

    apkFile = tempFolder.newFile("tiles.apk")
    def apkResource = getClass().getResourceAsStream("/tiles.apk")
    apkResource.withStream { input ->
      apkFile.append(input)
    }
  }

  def "maxApkSize < tiles.apk size, throw exception"() {
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
    project.apkSize {
      maxApkSize = 100 // size of tiles.apk is 1899702
    }

    when:
    project.evaluate()

    // Override APK file
    def task = project.tasks.getByName("sizeDebugDebugApk") as ApkSizeTask
    task.apk = apkFile
    task.execute()

    then:
    def e = thrown(GradleException)
    e.cause instanceof GradleException
    e.cause.message == "The current APK is 1899702 Kb, the current max is: 100 Kb."
  }

  def "maxApkSize > tiles.apk size, throw exception"() {
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
    project.apkSize {
      maxApkSize = 2000000 // size of tiles.apk is 1899702
    }

    when:
    project.evaluate()

    // Override APK file
    def task = project.tasks.getByName("sizeDebugDebugApk") as ApkSizeTask
    task.apk = apkFile
    task.execute()

    then:
    noExceptionThrown()
  }
}
