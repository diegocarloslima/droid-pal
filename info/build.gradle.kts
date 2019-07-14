import com.diegocarloslima.droidpal.build.Config
import com.diegocarloslima.droidpal.build.Deps

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Config.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = Config.compileSourceCompat
        targetCompatibility = Config.compileTargetCompat
    }

    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":base"))

    implementation(Deps.Android.material)

    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.constraintlayout)
    implementation(Deps.AndroidX.ktx)
    implementation(Deps.AndroidX.lifecycleExtensions)
    implementation(Deps.AndroidX.lifecycleReactiveStreams)
    kapt(Deps.AndroidX.lifecycleCompiler)
    implementation(Deps.AndroidX.navigationFragment)
    implementation(Deps.AndroidX.navigationUi)

    implementation(Deps.Firebase.core)

    implementation(Deps.AndroidPlayServices.adsId)
    implementation(Deps.AndroidPlayServices.base)

    implementation(Deps.Kotlin.stdlib)

    implementation(Deps.Coroutines.core)
    implementation(Deps.Coroutines.android)

    implementation(Deps.Dagger.dagger)
    implementation(Deps.Dagger.android)
    implementation(Deps.Dagger.androidSupport)
    kapt(Deps.Dagger.compiler)
    kapt(Deps.Dagger.androidProcessor)

    implementation(Deps.rootBeer)
    implementation(Deps.timber)

    testImplementation (Deps.junit)

    androidTestImplementation(Deps.AndroidX.testRunner)
    androidTestImplementation(Deps.AndroidX.testEspresso)
}