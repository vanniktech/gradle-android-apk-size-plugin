# gradle-android-apk-size-plugin

[![Build Status](https://travis-ci.org/vanniktech/gradle-android-apk-size-plugin.svg)](https://travis-ci.org/vanniktech/gradle-android-apk-size-plugin)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
![Java 8 required](https://img.shields.io/badge/java-8-brightgreen.svg)

Gradle plugin that generates CSV file with apk size per output and variant of apk.

This plugin creates a task per output file, per variant, and configures each task to run after that variant's assemble task. This means that if the assemble task does not run, no apk size will be reported. Reports can be found under `<projectbuildDir>/outputs/apksize/`.

Works with the latest Gradle Android Tools version 1.3.1. This plugin is compiled using Java 8 hence you also need Java 8 in order to use it.

# Set up

**app/build.gradle**

```groovy
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'com.vanniktech:gradle-android-apk-size-plugin:0.1.1'
    }
}

apply plugin: 'com.vanniktech.android.apk.size'
```

Information: [This plugin is also available on Gradle plugins](https://plugins.gradle.org/plugin/com.vanniktech.android.apk.size)

# License

Copyright (C) 2015 Vanniktech - Niklas Baudy

Licensed under the Apache License, Version 2.0