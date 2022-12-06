plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "org.compose.news.android"
    compileSdk = 32
    defaultConfig {
        applicationId = "org.compose.news.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
    }
    buildFeatures {
        viewBinding = true
    }
    lint {
        abortOnError = false
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))

    val nav_version = "2.5.0"
    val compose_version = "1.2.1"

    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.ui:ui-tooling:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.compose.foundation:foundation:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.activity:activity-ktx:1.5.0")


    //Constraint Layout
    //Constraint Layout
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0-alpha01")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02")



    // Compose Material Design
    implementation("androidx.compose.material:material:$compose_version")
    // Animations
    implementation("androidx.compose.animation:animation:$compose_version")
    // Tooling support (Previews, etc.)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    //
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")





    with(Deps.Koin) {
        implementation(core)
        implementation(android)
    }


    //Coil
    implementation("io.coil-kt:coil-compose:2.1.0")
    api("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    api("io.ktor:ktor-client-logging:1.6.6")


    implementation("androidx.compose.ui:ui-util:$compose_version")


    api("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1") {
        // https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-debug#debug-agent-and-android
        exclude(group = "org.jetbrains.kotlinx",module = "kotlinx-coroutines-debug")
    }
    testImplementation("app.cash.turbine:turbine:0.11.0")
    api("app.cash.turbine:turbine:0.11.0")

    implementation("androidx.lifecycle:lifecycle-process:2.5.1")
    implementation("androidx.lifecycle:lifecycle-service:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.5.1")
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:2.5.1")
    implementation("com.google.android.material:material:1.7.0-beta01")

    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

    // Jetpack Compose Integration
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Test rules and transitive dependencies:
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
// Needed for createAndroidComposeRule, but not createComposeRule:
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")


}