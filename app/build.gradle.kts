plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    kotlin("plugin.serialization") version "1.9.10"
}

android {
    namespace = "com.example.r8"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.r8"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
            isMinifyEnabled = providers.gradleProperty("isMinifyEnabled").map(String::toBoolean).get()
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
            if (providers.gradleProperty("proguardRule").map(String::toBoolean).getOrElse(false)) {
                proguardFile("proguard-serializable.pro")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit)
}