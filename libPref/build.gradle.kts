plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}
val libVersion = rootProject.extra["libVersion"] as String

android {
    compileSdk = 33
    buildToolsVersion = "33.0.0"
    namespace = "mt.pref"

    defaultConfig {
        minSdk = 23
        targetSdk = 33

        consumerProguardFiles.add(File("consumer-rules.pro"))

        aarMetadata {
            minCompileSdk = 23
        }
    }

    sourceSets {
        named("main") {
            java.srcDir("scr/main/java")
            res.srcDir("scr/main/res")
        }
    }

    buildFeatures {
        buildConfig = false
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles.apply {
                add(getDefaultProguardFile("proguard-android-optimize.txt"))
                add(File("proguard-rules.pro"))
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    implementation("androidx.annotation:annotation:1.3.0")
    compileOnly("androidx.appcompat:appcompat:1.3.1")
}

publishing {
    publications {
        create<MavenPublication>("release") {

            afterEvaluate {
                from(components["release"])
            }

            groupId = "com.github.chr56"
            artifactId = "mdPref"
            version = libVersion
        }
    }
}
