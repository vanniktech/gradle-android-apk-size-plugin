# gradle-android-apk-size-plugin

Gradle plugin that generates CSV files with apk size per output and variant of apk. The files can be found here `<projectbuildDir>/outputs/apksize/`. In addition while building it'll also print out the size of the apk.

Starting from version 0.3.0 the plugin can also be applied to Android library projects and hence it'll print out the aar size instead of the apk size.

This plugin creates a task per output file, per variant, and configures each task to run after the variant's assemble task. This means that if the assemble task does not run, no apk size will be reported.

Works with the latest stable Gradle Android Tools version 3.0.1.

# Set up

**app/build.gradle**

```gradle
buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath "com.vanniktech:gradle-android-apk-size-plugin:0.4.0"
  }
}

apply plugin: "com.vanniktech.android.apk.size"
```

Information: [This plugin is also available on Gradle plugins](https://plugins.gradle.org/plugin/com.vanniktech.android.apk.size)

### Snapshot

```gradle
buildscript {
  repositories {
    maven { url "https://oss.sonatype.org/content/repositories/snapshots" }
  }
  dependencies {
    classpath "com.vanniktech:gradle-android-apk-size-plugin:0.5.0-SNAPSHOT"
  }
}

apply plugin: "com.vanniktech.android.apk.size"
```

## Sample output

```
> ./gradlew assembleDebug

...buildspam...
:app:packageDebug
:app:zipalignDebug
:app:assembleDebug
Total APK Size in my-app-debug in bytes: 3231060

BUILD SUCCESSFUL

Total time: 33.017 secs
```

## Detailed output

```
<projectbuildDir>/outputs/apksize/debug.csv
<projectbuildDir>/outputs/apksize/release.csv
```

Those CSV files can for instance be used to configure the [Jenkins Plot Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Plot+Plugin), to see the apk size for each build in a graph.

## Configuration

Apksize is configurable via a Gradle extension (shown with default values) in `app/build.gradle`:

```groovy
apkSize {
  maxApkSize = 5800000
  teamcity = true
}
```

Configurable flags:
- `maxApkSize`: Set the max APK size in Kb. Greater than specified size will fail the build. Default is -1.
- `teamcity` : Enable teamcity statistics ready log output. Default is true if run on teamcity otherwise default is false.

## Teamcity

This will print logs in the form of
```
##teamcity[buildStatisticValue key='ApkSize_${apk.name}_B' value='{size in bytes}']
##teamcity[buildStatisticValue key='ApkSize_${apk.name}_MB' value='{size in megabytes}']
```

see https://confluence.jetbrains.com/display/TCD10/Build+Script+Interaction+with+TeamCity for
an in depth explanation how it works


# License

Copyright (C) 2015 Vanniktech - Niklas Baudy

Licensed under the Apache License, Version 2.0
