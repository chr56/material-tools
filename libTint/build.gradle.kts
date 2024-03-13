plugins {
    id("com.android.library")
    id("kotlin-android")
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
    implementation("androidx.appcompat:appcompat:1.6.1")
    compileOnly("com.google.android.material:material:1.6.1")
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
