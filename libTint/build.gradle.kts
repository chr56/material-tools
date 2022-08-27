plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}
val libVersion = rootProject.extra["libVersion"] as String

android {
    compileSdk = 33
    buildToolsVersion = "33.0.0"

    namespace = "mt.tint"

    defaultConfig {
        minSdk = 23
        targetSdk = 33

        consumerProguardFiles.add(File("consumer-rules.pro"))

        aarMetadata {
            minCompileSdk = 23
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    compileOnly("androidx.appcompat:appcompat:1.4.1")
    compileOnly("com.google.android.material:material:1.4.0")
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
