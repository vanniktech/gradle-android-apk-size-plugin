package com.vanniktech.android.apk.size

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class ApkSizePluginTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testNullProject() throws Exception {
        expectedException.expect(UnsupportedOperationException.class)
        expectedException.expectMessage("APK Size Plugin requires the Android Application plugin to be configured")

        new ApkSizePlugin().apply(null)
    }

    @Test
    public void testNotAndroidProject() {
        final Project project = ProjectBuilder.builder().build();

        expectedException.expect(UnsupportedOperationException.class)
        expectedException.expectMessage("APK Size Plugin requires the Android Application plugin to be configured")

        new ApkSizePlugin().apply(project)
    }
}