package com.vanniktech.android.apk.size

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.junit.Assert.assertTrue

class ApkSizeTaskTest {
    @Test
    public void canAddTaskToProject() {
        def project = ProjectBuilder.builder().build()
        def task = project.task('apkSize', type: ApkSizeTask)
        assertTrue(task instanceof ApkSizeTask)
    }

    @Test
    public void writesOutputFile() {
        def project = ProjectBuilder.builder().build()
        def task = project.task('apkSize', type: ApkSizeTask)
        def parent = new File("./src/test/assets")
        task.apk = new File(parent, "test.xml")
        task.outputFile = new File(parent, "test-result.csv")
        task.execute()

        def scanner = new Scanner(task.outputFile as File)
        def contents = new ArrayList<>()

        while (scanner.hasNextLine()) {
            contents.add(scanner.nextLine())
        }

        assert 2 == contents.size()

        assert "bytes,kilobytes,megabytes" == contents.get(0)
        assert "219,0.22,0" == contents.get(1)
    }
}