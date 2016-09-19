# gradle-android-apk-size-plugin

[![Build Status](https://travis-ci.org/vanniktech/gradle-android-apk-size-plugin.svg?branch=master)](https://travis-ci.org/vanniktech/gradle-android-apk-size-plugin?branch=master)
[![Codecov](https://codecov.io/github/vanniktech/gradle-android-apk-size-plugin/coverage.svg?branch=master)](https://codecov.io/github/vanniktech/gradle-android-apk-size-plugin?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
![Java 7 required](https://img.shields.io/badge/java-7-brightgreen.svg)

Gradle plugin that generates CSV files with apk size per output and variant of apk. The files can be found here `<projectbuildDir>/outputs/apksize/`. In addition while building it'll also print out the size of the apk.

Starting from version 0.3.0 the plugin can also be applied to Android library projects and hence it'll print out the aar size instead of the apk size.

This plugin creates a task per output file, per variant, and configures each task to run after the variant's assemble task. This means that if the assemble task does not run, no apk size will be reported.

Works with the latest stable Gradle Android Tools version 2.1.3. This plugin is compiled using Java 7 hence you also need Java 7 in order to use it.

# Set up

**app/build.gradle**

```groovy
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'com.vanniktech:gradle-android-apk-size-plugin:0.3.0'
    }
}

apply plugin: 'com.vanniktech.android.apk.size'
```

Information: [This plugin is also available on Gradle plugins](https://plugins.gradle.org/plugin/com.vanniktech.android.apk.size)

### Snapshots

Can be found [here](https://oss.sonatype.org/#nexus-search;quick~gradle-android-apk-size-plugin). Current one is:

```groovy
classpath 'com.vanniktech:gradle-android-apk-size-plugin:0.4.0-SNAPSHOT'
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

# License

Copyright (C) 2015 Vanniktech - Niklas Baudy

Licensed under the Apache License, Version 2.0