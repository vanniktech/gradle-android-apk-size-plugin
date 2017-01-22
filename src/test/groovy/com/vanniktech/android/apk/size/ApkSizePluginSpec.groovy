package com.vanniktech.android.apk.size

import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

final class ApkSizePluginSpec extends Specification {
    @Shared def project

    def 'setup'() {
        project = ProjectBuilder.builder().build()
    }

    def 'project is invalid'() {
        when:
        new ApkSizePlugin().apply project // apply plugin: 'com.vanniktech.android.apk.size'

        then:
        def e = thrown UnsupportedOperationException
        e.message == 'APK Size Plugin requires the Android Application or Library plugin to be configured'
    }

    @Unroll def '#projectPlugin project applied correctly'() {
        given:
        project.apply plugin: projectPlugin

        when:
        project.apply plugin: 'com.vanniktech.android.apk.size'

        then:
        noExceptionThrown()

        where:
        projectPlugin << ['com.android.application', 'com.android.library']
    }
}
