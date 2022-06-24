plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.KOTLIN_PARCELIZE)
    id(BuildPlugins.SAFE_ARGS)
    id(BuildPlugins.HILT)
}

android {
    compileSdk = Sdk.COMPILE_VERSION

    defaultConfig {
        minSdk = Sdk.MIN_VERSION
        targetSdk = Sdk.TARGET_VERSION

        applicationId = App.ID
        versionCode = App.VERSION_CODE
        versionName = App.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }

}

dependencies {
    // UI and Appcompat
    implementation(Libs.CORE_KTX)
    implementation(Libs.APP_COMPAT)
    implementation(Libs.MATERIAL)
    implementation(Libs.LIFECYCLE_RUNTIME)

    //Compose
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_TOOLING)
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.ACTIVITY_COMPOSE)
    implementation(Libs.COIL)
    // Coroutines
    implementation(Libs.COROUTINES_CORE)
    implementation(Libs.COROUTINES_ANDROID)

    // Navigation
    implementation(Libs.NAVIGATION_KTX)
    implementation(Libs.NAVIGATION_UI_KTX)
    implementation(Libs.NAVIGATION_COMPOSE)

    // Hilt
    implementation(Libs.HILT_ANDROID)
    kapt(Libs.HILT_COMPILER)
    implementation(Libs.HILT_NAVIGATION)

    // Retrofit
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_MOSHI)
    implementation(Libs.RETROFIT_SCALARS)
    implementation(Libs.OK_HTTP_LOGGING)

    // Moshi
    implementation(Libs.MOSHI)
    kapt(Libs.MOSHI_CODEGEN)

    implementation(Libs.GSON)

    // Test
    testImplementation(TestLibs.JUNIT)
    testImplementation(TestLibs.MOCKK)
    testImplementation(TestLibs.KOTEST_ASSERTIONS)
    testImplementation(TestLibs.CORE_TESTING)

    androidTestImplementation(TestLibs.COMPOSE_TESTING)
    androidTestImplementation(TestLibs.COMPOSE_JU)
    debugImplementation(TestLibs.COMPOSE_MANIFEST)

}