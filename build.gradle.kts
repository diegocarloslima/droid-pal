buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(com.diegocarloslima.droidpal.build.Deps.AndroidTools.gradle)
        classpath(com.diegocarloslima.droidpal.build.Deps.Kotlin.gradle)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}