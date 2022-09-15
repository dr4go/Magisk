plugins {
    id("com.android.application")
    id("io.michaelrocks.paranoid")
}

paranoid {
    obfuscationSeed = if (RAND_SEED != 0) RAND_SEED else null
    includeSubprojects = true
}

android {
    namespace = "com.topjohnwu.magisk"

    val canary = !Config.version.contains(".")

    val url = if (canary) null
    else "https://dr4go.github.io/download/magisk/${Config.version}.apk"

    defaultConfig {
        applicationId = "io.github.dr4go.main"
        versionCode = 1
        versionName = "1.0"
        buildConfigField("int", "STUB_VERSION", Config.stubVersion)
        buildConfigField("String", "APK_URL", url?.let { "\"$it\"" } ?: "null" )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

setupStub()

dependencies {
    implementation(project(":app:shared"))
}