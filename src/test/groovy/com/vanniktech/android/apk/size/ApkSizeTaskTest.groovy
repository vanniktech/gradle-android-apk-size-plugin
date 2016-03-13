package com.vanniktech.android.apk.size

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class ApkSizeTaskTest {
    @Test
    public void canAddTaskToProject() {
        def project = ProjectBuilder.builder().build()
        def task = project.task('apkSize', type: ApkSizeTask)
        assert task instanceof ApkSizeTask
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

        assert contents.size() == 2

        assert contents.get(0) == 'bytes,kilobytes,megabytes'
        assert contents.get(1) == '219,0.22,0'
    }
}