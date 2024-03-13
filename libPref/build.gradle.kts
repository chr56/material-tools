plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}
val libVersion = rootProject.extra["libVersion"] as String

android {
    compileSdk = 34
    buildToolsVersion = "34.0.0"
    namespace = "mt.pref"

    defaultConfig {
        minSdk = 23

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
    implementation("androidx.annotation:annotation:1.5.0")
    implementation("androidx.appcompat:appcompat-resources:1.6.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
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
