buildscript {
    extra.apply {
        // Library versions
        set("composeVersion", "1.2.0-beta02")
        set("coilVersion", "2.1.0")
        // App version information
        set("appVersion", "0.1.1") // The front facing version code, should follow SemVer
        set("appVersionCode", 3) // The internal code, used to determine updated vs less updated versions, should be incremented by 1 every new update
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.2.1" apply false
    id("com.android.library") version "7.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
