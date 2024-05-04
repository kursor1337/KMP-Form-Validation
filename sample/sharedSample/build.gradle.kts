plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.moko.resources)
    alias(libs.plugins.jetbrains.compose)
}

kotlin {
    androidTarget()

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "sharedSample"
            export(project(":kmm-form-validation"))
            export(libs.moko.resources)
            export(libs.decompose)
            export(libs.essenty.lifecycle)
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":kmm-form-validation"))
            implementation(libs.coroutines.core)
            api(libs.moko.resources)
            api(libs.decompose)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.moko.resources)
            implementation(libs.moko.resourcesCompose)
        }
    }
}

multiplatformResources {
    resourcesPackage.set("ru.mobileup.kmm_form_validation.sharedsample")
}

android {
    val minSdkVersion: Int = libs.versions.android.minSdk.get().toInt()
    val targetSdkVersion: Int = libs.versions.android.targetSdk.get().toInt()
    val compileSdkVersion: Int = libs.versions.android.compileSdk.get().toInt()

    namespace = "ru.mobileup.kmm_form_validation.sharedsample"
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
