plugins {
    id(BuildPlugins.ANDROID_APPLICATION) version BuildVersions.AGP apply false
    id(BuildPlugins.KOTLIN_ANDROID) version BuildVersions.KOTLIN apply false
    id(BuildPlugins.SAFE_ARGS) version Versions.NAVIGATION apply false
    id(BuildPlugins.HILT) version Versions.HILT apply false
}
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    }
}