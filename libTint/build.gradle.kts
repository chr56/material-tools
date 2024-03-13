plugins {
    alias(libs.plugins.androidGradlePluginLibrary)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}
val libVersion = rootProject.extra["libVersion"] as String

android {
    compileSdk = 34
    buildToolsVersion = "34.0.0"

    namespace = "mt.tint"

    defaultConfig {
        minSdk = 23

        consumerProguardFiles.add(File("consumer-rules.pro"))

        aarMetadata {
            minCompileSdk = 23
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
    api(projects.libPref)
    api(projects.libColorRes)

    implementation(libs.androidx.appcompat)
    compileOnly(libs.androidx.constraintlayout)
    compileOnly(libs.google.material)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
            groupId = "com.github.chr56"
            artifactId = "mdTint"
            version = libVersion
        }
    }
}
