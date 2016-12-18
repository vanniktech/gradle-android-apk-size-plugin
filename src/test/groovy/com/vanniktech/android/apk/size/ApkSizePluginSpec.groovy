package com.vanniktech.android.apk.size

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class ApkSizePluginSpec extends Specification {
    def 'nullProject'() {
        when:
        new ApkSizePlugin().apply(null)

        then:
        UnsupportedOperationException exception = thrown()
        exception.message == 'APK Size Plugin requires the Android Application or Library plugin to be configured'
    }

    def 'notAndroidProject'() {
        given:
        Project project = ProjectBuilder.builder().build()

        when:
        new ApkSizePlugin().apply(project)

        then:
        UnsupportedOperationException exception = thrown()
        exception.message == 'APK Size Plugin requires the Android Application or Library plugin to be configured'
    }

    def 'androidProject'() {
        given:
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'com.android.application'

        when:
        new ApkSizePlugin().apply(project)

        then:
        noExceptionThrown()
    }

    def 'androidLibrary'() {
        given:
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'com.android.library'

        when:
        new ApkSizePlugin().apply(project)

        then:
        noExceptionThrown()
    }
}
