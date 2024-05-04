import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.jetbrains.compose)
}

group = "ru.mobileup.kmm_form_validation.desktop_sample"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvmToolchain(8)
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.material3)
    implementation(libs.decompose)
    implementation(libs.decompose.compose)
    implementation(libs.coroutines.swing)
    implementation(project(":sample:sharedSample"))
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ru.mobileup.kmm_form_validation.desktop_sample"
            packageVersion = "1.0.0"
        }
    }
}
