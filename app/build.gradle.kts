import com.diegocarloslima.droidpal.build.Config
import com.diegocarloslima.droidpal.build.Deps

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Config.compileSdkVersion)

    defaultConfig {
        applicationId = "com.diegocarloslima.droidpal"
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.constraintlayout)
    implementation(Deps.AndroidX.core)
    implementation(Deps.GoogleAndroid.material)
    implementation(Deps.Kotlin.stdlib)

    testImplementation (Deps.junit)

    androidTestImplementation(Deps.AndroidX.testRunner)
    androidTestImplementation(Deps.AndroidX.testEspresso)
}