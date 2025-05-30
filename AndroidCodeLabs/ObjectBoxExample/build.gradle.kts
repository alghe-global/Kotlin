// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}

buildscript {
    val objectBoxVersion by extra("3.8.0")

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("io.objectbox:objectbox-gradle-plugin:$objectBoxVersion")
    }
}