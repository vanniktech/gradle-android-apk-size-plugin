package com.vanniktech.android.apk.size

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import org.junit.Test

import static org.junit.Assert.assertTrue

class ApkSizeTaskTest {
    @Test
    public void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('apkSize', type: ApkSizeTask)
        assertTrue(task instanceof ApkSizeTask)
    }
}