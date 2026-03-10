plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.expensemanager"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.expensemanager"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.google.android.material:material:1.8.0")


    implementation ("com.google.firebase:firebase-analytics:21.5.1")

    implementation("com.google.firebase:firebase-database-ktx:21.0.0")
    implementation("com.google.firebase:firebase-auth:23.2.0")



    implementation("com.firebaseui:firebase-ui-database:8.0.2")

    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
}

apply(plugin = "com.google.gms.google-services")
