plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id ("kotlin-kapt")
}


android {
    namespace = "com.daniebeler.pixelix"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.daniebeler.pixelix"
        minSdk = 26
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("com.android.volley:volley:1.2.1")
    implementation("com.google.code.gson:gson:2.10")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")


    implementation ("com.google.dagger:hilt-android:2.45")
    kapt ("com.google.dagger:hilt-android-compiler:2.45")

    implementation ("io.coil-kt:coil-compose:2.5.0")

    implementation ("androidx.navigation:navigation-compose:2.7.6")

    implementation ("androidx.compose.material3:material3:1.2.0-beta01")

    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation ("androidx.browser:browser:1.7.0")

    implementation ("androidx.compose.material:material-icons-extended")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.1")

    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")

    implementation ("com.google.android.material:material:1.11.0")

}

kapt {
    correctErrorTypes = true
}
