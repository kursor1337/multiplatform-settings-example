pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            version("decompose", "1.0.0")
            library("decompose-core", "com.arkivanov.decompose", "decompose").versionRef("decompose")
            library(
                "decompose-compose",
                "com.arkivanov.decompose",
                "extensions-compose-jetpack"
            ).versionRef("decompose")

            library("essenty-lifecycle", "com.arkivanov.essenty", "lifecycle").version("1.0.0")

            version("koin", "3.3.0")
            library("koin-core", "io.insert-koin", "koin-core").versionRef("koin")

            // Storage
            library(
                "multiplatform-settings-core",
                "com.russhwolf",
                "multiplatform-settings"
            ).version("1.0.0")

            library(
                "multiplatform-settings-noarg",
                "com.russhwolf",
                "multiplatform-settings-no-arg"
            ).version("1.0.0")

            // ---ANDROID---

            // Compose
            version("compose", "1.3.1")
            library("compose-ui", "androidx.compose.ui", "ui").versionRef("compose")
            library("compose-tooling", "androidx.compose.ui", "ui-tooling").versionRef("compose")
            library(
                "compose-preview",
                "androidx.compose.ui",
                "ui-tooling-preview"
            ).versionRef("compose")
            library(
                "compose-foundation",
                "androidx.compose.foundation",
                "foundation"
            ).versionRef("compose")
            library("compose-material", "androidx.compose.material", "material").versionRef("compose")
            library("compose-activity", "androidx.activity", "activity-compose").version("1.5.1")
            bundle(
                "compose",
                listOf(
                    "compose-ui",
                    "compose-tooling",
                    "compose-preview",
                    "compose-foundation",
                    "compose-material",
                    "compose-activity"
                )
            )

            // Excrypted shared spref
            library("android-security", "androidx.security", "security-crypto").version("1.1.0-alpha04")

            library("coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-core").version("1.6.4")
        }
    }
}

rootProject.name = "multiplatform-settings-example"
include(":androidApp")
include(":shared")