package com.vanniktech.android.apk.size

open class ApkSizeExtension {
  /**
   * Set the max APK size in Kb. Greater than specified size will fail the build. Default is -1.
   */
  var maxApkSize = -1
  var teamcity = false
}
