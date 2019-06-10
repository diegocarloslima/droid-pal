package com.diegocarloslima.droidpal.build

object Deps : BaseDep() {

    val timber = "com.jakewharton.timber:timber" version "4.7.1"

    val junit = "junit:junit" version "4.12"

    object AndroidTools : BaseDep("com.android.tools") {
        val gradle = dep("build:gradle", "3.4.1")
    }

    object AndroidX : BaseDep("androidx") {
        private val lifecycleVersion = "2.1.0-beta01"
        private val navigationVersion = "2.0.0"

        val appcompat = dep("appcompat:appcompat", "1.0.2")
        val constraintlayout = dep("constraintlayout:constraintlayout", "2.0.0-beta1")
        val ktx = dep("core:core-ktx", "1.1.0-beta01")
        val lifecycleExtensions = dep("lifecycle:lifecycle-extensions", lifecycleVersion)
        val lifecycleReactiveStreams = dep("lifecycle:lifecycle-reactivestreams", lifecycleVersion)
        val lifecycleCompiler = dep("lifecycle:lifecycle-compiler", lifecycleVersion)
        val navigationFragment = dep("navigation:navigation-fragment-ktx", navigationVersion)
        val navigationUi = dep("navigation:navigation-ui-ktx", navigationVersion)

        val testRunner = dep("test:runner", "1.2.0")
        val testEspresso = dep("test.espresso:espresso-core", "3.2.0")
    }

    object Android : BaseDep("com.google.android") {
        val material = dep("material:material", "1.0.0")
    }

    object AndroidPlayServices : BaseDep("com.google.android.gms") {
        val adsId = dep(name = "play-services-ads-identifier", version = "16.0.0")
        val base = dep(name = "play-services-base", version = "16.1.0")
    }

    object PlayServices : BaseDep("com.google.gms") {
        // TODO: Version 4.2.0 causes a crash
        // https://github.com/google/play-services-plugins/issues/22
        val googleServices = dep(name = "google-services", version = "4.0.2")
    }

    object Firebase : BaseDep("com.google.firebase") {
        val core = dep(name = "firebase-core", version = "16.0.9")
        val messaging = dep(name = "firebase-messaging", version = "18.0.0")
    }

    object Kotlin : BaseDep("org.jetbrains.kotlin", "1.3.31") {
        val gradle = dep(name = "kotlin-gradle-plugin")
        val stdlib = dep(name = "kotlin-stdlib-jdk7")
    }

    object Dagger : BaseDep("com.google.dagger", "2.23") {
        val dagger = dep(name = "dagger")
        val android = dep(name = "dagger-android")
        val androidSupport = dep(name = "dagger-android-support")
        val compiler = dep(name = "dagger-compiler")
        val androidProcessor = dep(name = "dagger-android-processor")
    }
}

abstract class BaseDep(private val groupPrefix: String = "", private val defaultVersion: String = "") {

    protected fun dep(groupName: String = "", version: String = "", name: String = ""): String {
        val depGroupName = if (groupName.isNotEmpty()) "$groupPrefix.$groupName" else "$groupPrefix:$name"
        val depVersion = if (version.isNotEmpty()) version else defaultVersion
        return "$depGroupName:$depVersion"
    }
}

private infix fun String.version(version: String) = "$this:$version"
