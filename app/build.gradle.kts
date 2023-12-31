plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.7.10"
}

android {
    namespace = "com.example.nfts_application"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.nfts_application"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
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
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val nav_version = "2.5.3"
    val ktor_version = "2.3.5"

    implementation ("androidx.compose.material:material-icons-extended:1.0.0-alpha10")
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation ("dev.chrisbanes.accompanist:accompanist-coil:0.6.1")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation ("com.google.accompanist:accompanist-flowlayout:0.20.0")

    implementation ("io.ktor:ktor-client-core:2.1.2")
    implementation ("io.ktor:ktor-client-cio:2.1.2")
    implementation ("io.ktor:ktor-client-serialization:2.1.2")
    implementation ("io.ktor:ktor-client-logging:2.1.2")
    implementation ("io.ktor:ktor-client-content-negotiation:2.1.2")
    implementation ("io.ktor:ktor-serialization-kotlinx-json:2.1.2")
    implementation ("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2-native-mt")

    implementation ("io.metamask.androidsdk:metamask-android-sdk:0.2.1")
    


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}