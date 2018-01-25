package com.vanniktech.android.apk.size

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.DomainObjectCollection
import org.gradle.api.Plugin
import org.gradle.api.Project

class ApkSizePlugin : Plugin<Project> {
  override fun apply(project: Project) {
    when {
      project.plugins.hasPlugin("com.android.application") -> applyAndroidProject(project, project.extensions.findByType(AppExtension::class.java)?.applicationVariants)
      project.plugins.hasPlugin("com.android.library") -> applyAndroidProject(project, project.extensions.findByType(LibraryExtension::class.java)?.libraryVariants)
      else -> throw UnsupportedOperationException("APK Size Plugin requires the Android Application or Library plugin to be configured")
    }
  }

  private fun <T : BaseVariant> applyAndroidProject(project: Project, variants: DomainObjectCollection<T>?) {
    project.extensions.create("apkSize", ApkSizeExtension::class.java)

    variants?.all { variant ->
      variant.outputs.forEach { output ->
        var slug = variant.name.capitalize()
        var path = "${project.buildDir}/outputs/apksize/${variant.name}"

        if (!variant.outputs.isEmpty()) {
          slug += output.name.capitalize()
          path += "/${output.name}"
        }

        // Read from extension
        val extension = project.extensions.findByName("apkSize") as ApkSizeExtension

        // Create task
        val task = project.tasks.create("size${slug}Apk", ApkSizeTask::class.java)
        task.description = "Outputs APK / AAR size for ${variant.name} variant."
        task.group = "Reporting"
        task.apk = output.outputFile
        task.outputFile = project.file(path + ".csv")
        task.extension = extension

        variant.assemble.doLast {
          task.sizeApk()
        }
      }
    }
  }
}
