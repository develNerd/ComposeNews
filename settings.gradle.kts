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

rootProject.name = "FidoNews"
include(":androidApp")
include(":shared")