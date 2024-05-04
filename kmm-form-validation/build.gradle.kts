plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
}

apply {
    from("$rootDir/publish.gradle")
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.coroutines.core)
            implementation(libs.moko.resources)
            implementation(compose.foundation)
            implementation(compose.ui)
        }
    }
}

android {
    val minSdkVersion: Int = libs.versions.android.minSdk.get().toInt()
    val targetSdkVersion: Int = libs.versions.android.targetSdk.get().toInt()
    val compileSdkVersion: Int = libs.versions.android.compileSdk.get().toInt()

    namespace = "ru.mobileup.kmm_form_validation"
    compileSdk = compileSdkVersion
    defaultConfig {
        minSdk = minSdkVersion
        targetSdk = targetSdkVersion
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}