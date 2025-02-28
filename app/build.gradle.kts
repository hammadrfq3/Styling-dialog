plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    `maven-publish`
}

android {
    publishing {
        singleVariant("release") {
            withJavadocJar()
            withSourcesJar()
        }
        singleVariant("debug") {
            withJavadocJar()
            withSourcesJar()
        }
    }
    namespace = "comcustom.dialog"
    compileSdk = 34

    defaultConfig {
        applicationId = "comcustom.dialog"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

publishing {
    publications {
        /*register<MavenPublication>("release") {
            groupId = "com.github.hammadrfq3"
            artifactId = "Styling-dialog-compose"
            version = "1.0"

            afterEvaluate {
                from(components["release"])
            }
        }*/
        create<MavenPublication>("release") {
            groupId = "com.github.hammadrfq3"
            artifactId = "Styling-dialog-compose"
            version = "1.0"

            afterEvaluate {
                from(components["release"])
                artifact("$buildDir/outputs/aar/${project.name}-debug.aar") {
                    classifier = "debug"
                }
            }
        }
    }
    /*publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.hammadrfq3"
            artifactId = "Styling-dialog-compose"
            version = "1.2"

            from(components["release"])
        }
    }*/
}