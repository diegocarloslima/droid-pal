package com.diegocarloslima.droidpal.build

import org.gradle.api.JavaVersion

object Config {
    val compileSdkVersion = 28
    val minSdkVersion = 19
    val targetSdkVersion = 28

    val compileSourceCompat = JavaVersion.VERSION_1_8
    val compileTargetCompat = JavaVersion.VERSION_1_8
}