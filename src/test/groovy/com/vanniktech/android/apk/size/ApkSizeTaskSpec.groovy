package com.vanniktech.android.apk.size

import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

final class ApkSizeTaskSpec extends Specification {
    def project

    def "setup"() {
        project = ProjectBuilder.builder().build()
    }

    def "can add task to project"() {
        expect:
        project.task("apkSize", type: ApkSizeTask) instanceof ApkSizeTask
    }

    def "writes output file"() {
        given:
        ApkSizeTask task = project.task "apkSize", type: ApkSizeTask
        def parent = new File("./src/test/assets")
        task.apk = new File(parent, "test.xml")
        task.outputFile = new File(parent, "test-result.csv")
        task.extension = new ApkSizeExtension(maxApkSize: 500)

        when:
        task.execute()

        def scanner = new Scanner(task.outputFile)
        def contents = []
        while (scanner.hasNextLine()) {
            contents.add(scanner.nextLine())
        }

        then:
        contents.size() == 2
        contents.get(0) == "bytes,kilobytes,megabytes"
        contents.get(1) == "219,0.22,0"
    }
}
