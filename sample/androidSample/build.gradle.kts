plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.compose)
}

android {
    val minSdkVersion: Int = libs.versions.android.minSdk.get().toInt()
    val targetSdkVersion: Int = libs.versions.android.targetSdk.get().toInt()
    val compileSdkVersion: Int = libs.versions.android.compileSdk.get().toInt()

    namespace = "ru.mobileup.kmm_form_validation.android_sample"
    compileSdk = compileSdkVersion

    defaultConfig {
        applicationId = "ru.mobileup.kmm_form_validation.android_sample"
        minSdk = minSdkVersion
        targetSdk = targetSdkVersion
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.moko.resourcesCompose)
    implementation(compose.ui)
    implementation(compose.material3)
    implementation(libs.decompose)
    implementation(libs.decompose.compose)
    implementation(project(":sample:sharedSample"))
}