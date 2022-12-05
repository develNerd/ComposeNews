pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}



dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ComposeNews"
include(":androidApp")
include(":shared")