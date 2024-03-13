plugins {
    alias(libs.plugins.androidGradlePluginLibrary)
    id("maven-publish")
}
val libVersion = rootProject.extra["libVersion"] as String

android {
    compileSdk = 34
    buildToolsVersion = "34.0.0"
    namespace = "mt.color"

    defaultConfig {
        minSdk = 23

        consumerProguardFiles.add(File("consumer-rules.pro"))

        aarMetadata {
            minCompileSdk = 23
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

    sourceSets {
        named("main") {
            java.srcDir("scr/main/java")
            res.srcDir("scr/main/res")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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
    implementation(libs.androidx.annotation)
}

publishing {
    publications {
        create<MavenPublication>("release") {

            afterEvaluate {
                from(components["release"])
            }

            groupId = "com.github.chr56"
            artifactId = "mdColorRes"
            version = libVersion
        }
    }
}
