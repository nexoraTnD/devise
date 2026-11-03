plugins { id("com.android.application"); id("org.jetbrains.kotlin.android") }

android { namespace = "com.example.currencyconverter"; compileSdk = 34
    defaultConfig { applicationId = "com.example.currencyconverter"; minSdk = 23; targetSdk = 34; versionCode = 1; versionName = "1.0" }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures { viewBinding = true }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
}
