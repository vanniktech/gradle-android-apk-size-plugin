package com.vanniktech.android.apk.size

import com.android.build.gradle.api.BaseVariant
import org.gradle.api.DomainObjectCollection
import org.gradle.api.Plugin
import org.gradle.api.Project

class ApkSizePlugin implements Plugin<Project> {
    @Override
    void apply(final Project project) {
        if (project.plugins.hasPlugin('com.android.application')) {
            ApkSizePlugin.applyAndroidProject(project, (DomainObjectCollection<BaseVariant>) project.android.applicationVariants)
        } else {
            throw new IllegalArgumentException("Apk Size Plugin requires the Android Application plugin to be configured")
        }
    }

    private static void applyAndroidProject(Project project, DomainObjectCollection<BaseVariant> variants) {
        variants.all { variant ->
            variant.outputs.each { output ->
                def slug = variant.name.capitalize()
                def path = "${project.buildDir}/outputs/apksize/${variant.name}"

                if (variant.outputs.size() > 1) {
                    slug += output.name.capitalize()
                    path += "/${output.name}"
                }

                ApkSizeTask task = project.tasks.create("size${slug}Apk", ApkSizeTask)
                task.apk = output
                task.outputFile = project.file(path + '.csv')
                variant.assemble.doLast { task.sizeApk() }
            }
        }
    }
}
