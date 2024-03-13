plugins {
    alias(libs.plugins.androidGradlePluginLibrary)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}
val libVersion = rootProject.extra["libVersion"] as String

android {

    compileSdk = 34
    buildToolsVersion = "34.0.0"

    namespace = "mt.views"

    defaultConfig {
        minSdk = 24

        consumerProguardFiles("consumer-rules.pro")

        aarMetadata {
            minCompileSdk = 24
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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

    publishing {
        publishing {
            singleVariant("release") {
                withSourcesJar()
            }
        }
    }
}

dependencies {
    api(projects.libUtil)
    api(projects.libTint)
    api(projects.libPref)
    api(projects.libColorRes)
    compileOnly(libs.androidx.appcompat)
    compileOnly(libs.androidx.recyclerview)
    compileOnly(libs.androidx.fragment)
    compileOnly(libs.androidx.activity)
    compileOnly(libs.androidx.preference)
}


publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
            groupId = "com.github.chr56"
            artifactId = "mdView"
            version = libVersion
        }
    }
}

