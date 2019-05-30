buildscript {
    extra.apply {
        // SDK
        set("compileSdkVersion", 28)
        set("minSdkVersion", 19)
        set("targetSdkVersion", 28)


        // Dependencies
        set("kotlin_version", "1.3.31")
    }

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.4.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlin_version"]}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}