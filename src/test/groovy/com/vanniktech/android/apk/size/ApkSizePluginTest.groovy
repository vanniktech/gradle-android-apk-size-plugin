package com.vanniktech.android.apk.size

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class ApkSizePluginTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none()

    @Test
    public void nullProject() throws Exception {
        expectedException.expect(UnsupportedOperationException.class)
        expectedException.expectMessage('APK Size Plugin requires the Android Application or Library plugin to be configured')

        new ApkSizePlugin().apply(null)
    }

    @Test
    public void notAndroidProject() {
        def project = ProjectBuilder.builder().build()

        expectedException.expect(UnsupportedOperationException.class)
        expectedException.expectMessage('APK Size Plugin requires the Android Application or Library plugin to be configured')

        new ApkSizePlugin().apply(project)
    }

    @Test
    public void androidProject() {
        def project = ProjectBuilder.builder().build()
        project.plugins.apply('com.android.application')

        new ApkSizePlugin().apply(project)
    }

    @Test
    public void androidLibrary() {
        def project = ProjectBuilder.builder().build()
        project.plugins.apply('com.android.library')

        new ApkSizePlugin().apply(project)
    }
}