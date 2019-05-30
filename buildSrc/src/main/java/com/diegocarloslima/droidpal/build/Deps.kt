package com.diegocarloslima.droidpal.build

object Deps : BaseDep() {

    val junit = "junit:junit" version "4.12"

    object AndroidTools : BaseDep("com.android.tools") {
        val gradle = dep("build:gradle", "3.4.1")
    }

    object AndroidX : BaseDep("androidx", "1.0.2") {
        val appcompat = dep("appcompat:appcompat")
        val core = dep("core:core-ktx")
        val constraintlayout = dep("constraintlayout:constraintlayout", "1.1.3")

        val testRunner = dep("test:runner")
        val testEspresso = dep("test.espresso:espresso-core", "3.2.0")
    }

    object GoogleAndroid : BaseDep("com.google.android") {
        val material = dep("material:material", "1.0.0")
    }

    object Kotlin : BaseDep("org.jetbrains.kotlin", "1.3.31") {
        val gradle = dep(name = "kotlin-gradle-plugin")
        val stdlib = dep(name = "kotlin-stdlib-jdk7")
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
