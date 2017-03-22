package com.vanniktech.android.apk.size

import com.android.build.gradle.api.BaseVariant
import org.gradle.api.DomainObjectCollection
import org.gradle.api.Plugin
import org.gradle.api.Project

final class ApkSizePlugin implements Plugin<Project> {
    @Override void apply(Project project) {
        if (project.plugins.hasPlugin("com.android.application")) {
            applyAndroidProject(project, project.android.applicationVariants)
        } else if (project.plugins.hasPlugin("com.android.library")) {
            applyAndroidProject(project, project.android.libraryVariants)
        } else {
            throw new UnsupportedOperationException("APK Size Plugin requires the Android Application or Library plugin to be configured")
        }
    }

    static applyAndroidProject(Project project, DomainObjectCollection<BaseVariant> variants) {
        project.extensions.create("apkSize", ApkSizeExtension)

        variants.all { variant ->
            variant.outputs.each { output ->
                def slug = variant.name.capitalize()
                def path = "${project.buildDir}/outputs/apksize/${variant.name}"

                if (!variant.outputs.empty) {
                    slug += output.name.capitalize()
                    path += "/${output.name}"
                }

                // Read from extension
                final extension = project.extensions["apkSize"] as ApkSizeExtension

                // Create task
                final task = project.tasks.create "size${slug}Apk", ApkSizeTask
                task.description = "Outputs APK / AAR size for ${variant.name} variant."
                task.group = "Reporting"
                task.apk = output.outputFile
                task.outputFile = project.file path + ".csv"
                task.extension = extension

                variant.assemble.doLast {
                    task.sizeApk()
                }
            }
        }
    }
}
