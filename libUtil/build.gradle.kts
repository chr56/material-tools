plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}
val libVersion = rootProject.extra["libVersion"] as String

android {

    compileSdk = 33
    buildToolsVersion = "33.0.0"

    namespace = "mt.util"

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
    buildTypes {
        named("release") {
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
    buildFeatures {
        buildConfig = false
    }

    publishing {
        publishing {
            singleVariant("release") {
                withSourcesJar()
            }
        }
    }

    afterEvaluate {
        tasks.withType(JavaCompile::class.java) {
            options.compilerArgs.apply {
                // add("-Xlint:deprecation")
            }
        }
    }
}

dependencies {
    api(projects.libColorRes)
    compileOnly("androidx.appcompat:appcompat:1.6.1")
}

publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
            groupId = "com.github.chr56"
            artifactId = "mdUtil"
            version = libVersion
        }
    }
}
