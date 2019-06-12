buildscript {

    repositories {
        google()
        jcenter()

        maven {
            setUrl("https://maven.fabric.io/public")
        }
    }

    dependencies {
        // It doesn't work with imports, using full qualified name instead
        classpath(com.diegocarloslima.droidpal.build.Deps.AndroidTools.gradle)
        classpath(com.diegocarloslima.droidpal.build.Deps.Kotlin.gradle)
        classpath(com.diegocarloslima.droidpal.build.Deps.Crashlytics.gradle)
        classpath(com.diegocarloslima.droidpal.build.Deps.PlayServices.googleServices)
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