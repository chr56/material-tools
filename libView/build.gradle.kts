plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}
val libVersion = rootProject.extra["libVersion"] as String

android {

    compileSdk = 33
    buildToolsVersion = "33.0.0"

    namespace = "mt.views"

    defaultConfig {
        minSdk = 24
        targetSdk = 33

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
    compileOnly("androidx.appcompat:appcompat:1.6.1")
    compileOnly("androidx.preference:preference:1.2.0")
//    compileOnly("com.google.android.material:material:1.6.1")
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

