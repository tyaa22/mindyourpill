plugins {
    id("com.android.application")
}

android {
    namespace = "com.project.mindyourpillnew"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.project.mindyourpillnew"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

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
    buildFeatures {
        viewBinding = true
    }
    configurations.all {

    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.4.0") {
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk8")
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk7")
    }
    implementation("com.google.android.material:material:1.4.0") {
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk8")
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk7")
    }
    implementation("androidx.constraintlayout:constraintlayout:2.0.0") {
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk8")
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk7")
    }
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1") {
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk8")
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk7")
    }
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1") {
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk8")
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk7")
    }
    implementation("androidx.navigation:navigation-fragment:2.3.5") {
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk8")
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk7")
    }
    implementation("androidx.navigation:navigation-ui:2.3.5") {
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk8")
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk7")
    }
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.0-m1") {
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk8")
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk7")
    }
    implementation("androidx.annotation:annotation:1.6.0")
    testImplementation("junit:junit:4.13.2") {
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk8")
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk7")
    }
    androidTestImplementation("androidx.test.ext:junit:1.1.5") {
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk8")
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk7")
    }
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") {
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk8")
        exclude(group="org.jetbrains.kotlin", module= "kotlin-stdlib-jdk7")
    }
}

