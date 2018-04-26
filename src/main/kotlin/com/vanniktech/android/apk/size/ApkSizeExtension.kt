package com.vanniktech.android.apk.size

open class ApkSizeExtension {
  /**
   * Set the max APK size in Kb. Greater than specified size will fail the build. Default is -1.
   */
  var maxApkSize = -1

  /**
   * Enable this to get teamcity ready statistic reporting in your logs
   * This will print logs in the form of
   * ##teamcity[buildStatisticValue key='ApkSize_${apk.name}_B' value='{size in bytes}']
   * ##teamcity[buildStatisticValue key='ApkSize_${apk.name}_MB' value='{size in megabytes}']
   *
   * see https://confluence.jetbrains.com/display/TCD10/Build+Script+Interaction+with+TeamCity for
   * an in depth explanation how it works
   */
  var teamcity = false
}
