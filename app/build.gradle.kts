plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.2"

    defaultConfig {
        applicationId = "io.github.wellingtoncosta.customviews"
        minSdkVersion(16)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isTestCoverageEnabled = true

            buildConfigField(
                "String",
                "API_URL",
                "\"https://api.github.com\""
            )
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            isZipAlignEnabled = true

            proguardFile(getDefaultProguardFile("proguard-android.txt"))
            proguardFile(file("proguard-rules.pro"))

            buildConfigField(
                "String",
                "API_URL",
                "\"https://api.github.com\""
            )
        }
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        val commonTest = "src/commonTest/java"
        getByName("androidTest").java.srcDirs(commonTest)
        getByName("test").java.srcDirs(commonTest)
    }
}

configurations {
    all { resolutionStrategy.force("junit:junit:4.13") }
}

dependencies {
    // Android
    implementation("androidx.core:core-ktx:1.2.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.lifecycle:lifecycle-livedata:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.2.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")

    // Fuel
    implementation("com.github.kittinunf.fuel:fuel:2.2.1")
    implementation("com.github.kittinunf.fuel:fuel-coroutines:2.2.1")
    implementation("com.github.kittinunf.fuel:fuel-kotlinx-serialization:2.2.1")

    // Koin
    implementation("org.koin:koin-androidx-scope:2.0.1")
    implementation("org.koin:koin-androidx-viewmodel:2.0.1")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.61")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.14.0")

    // Google Material Design
    implementation("com.google.android.material:material:1.1.0")

    // Simple Stack
    implementation("com.github.Zhuinden:simple-stack:2.2.4")

    // Testing
    testImplementation("junit:junit:4.13")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("com.squareup.okhttp3:okhttp-tls:4.3.1")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.3.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.3")

    androidTestImplementation("org.koin:koin-test:2.0.1")
    androidTestImplementation("androidx.test:rules:1.2.0")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("com.squareup.okhttp3:okhttp-tls:4.3.1")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.3.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
