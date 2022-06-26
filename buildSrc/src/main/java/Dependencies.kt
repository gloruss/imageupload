object Versions{

    const val COROUTINES = "1.5.2"
    const val APP_COMPAT = "1.4.1"
    const val CORE_KTX = "1.6.0"
    const val CONSTRAINT_LAYOUT = "2.1.1"
    const val NAVIGATION = "2.4.1"
    const val MATERIAL = "1.4.0"
    const val HILT = "2.38.1"
    const val RETROFIT = "2.9.0"
    const val OK_HTTP = "4.9.2"
    const val MOSHI = "1.13.0"
    const val JUNIT = "4.13.2"
    const val MOCKK = "1.12.0"
    const val KOTEST = "4.6.3"
    const val CORE_TESTING = "2.1.0"
    const val COMPOSE ="1.2.0-beta02"
    const val ACTIVITY_COMPOSE="1.3.1"
    const val LIFECYCLE ="2.4.0"
    const val COIL = "2.0.0-rc02"
    const val GSON = "2.8.2"
    const val HILT_COMPOSE = "1.0.0"
}

object BuildVersions {
    const val AGP = "7.0.4"
    const val KOTLIN = "1.6.10"
}

object BuildPlugins {
    const val ANDROID_APPLICATION = "com.android.application"
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val KOTLIN_KAPT = "org.jetbrains.kotlin.kapt"
    const val KOTLIN_PARCELIZE = "org.jetbrains.kotlin.plugin.parcelize"
    const val SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
    const val HILT = "dagger.hilt.android.plugin"
}


object Libs {
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val NAVIGATION_KTX = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_COMPOSE ="androidx.navigation:navigation-compose:${Versions.NAVIGATION}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
    const val RETROFIT_SCALARS = "com.squareup.retrofit2:converter-scalars:${Versions.RETROFIT}"
    const val OK_HTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.OK_HTTP}"
    const val MOSHI = "com.squareup.moshi:moshi:${Versions.MOSHI}"
    const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI}"
    const val COMPOSE_UI="androidx.compose.ui:ui:${Versions.COMPOSE}"
    const val COMPOSE_MATERIAL="androidx.compose.material:material:${Versions.COMPOSE}"
    const val COMPOSE_TOOLING="androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
    const val ACTIVITY_COMPOSE="androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}"
    const val LIFECYCLE_RUNTIME="androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
    const val COIL ="io.coil-kt:coil-compose:${Versions.COIL}"
    const val GSON =  "com.google.code.gson:gson:${Versions.GSON}"
    const val HILT_NAVIGATION = "androidx.hilt:hilt-navigation-compose:${Versions.HILT_COMPOSE}"
}

object TestLibs {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
    const val KOTEST_ASSERTIONS = "io.kotest:kotest-assertions-core-jvm:${Versions.KOTEST}"
    const val CORE_TESTING = "androidx.arch.core:core-testing:${Versions.CORE_TESTING}"
    const val COMPOSE_TESTING ="androidx.compose.ui:ui-test:${Versions.COMPOSE}"
    const val COMPOSE_JU ="androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
    const val COMPOSE_MANIFEST="androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE}"

}