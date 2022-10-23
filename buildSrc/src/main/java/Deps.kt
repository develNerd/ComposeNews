import Versions.dateTimeVersion
import Versions.ktorVersion
import Versions.serializationVersion

object Versions {
    const val koin = "3.2.0"
    const val kermit = "1.1.3"
    const val ktorVersion = "2.1.1"
    const val  serializationVersion = "1.4.0"
    const val dateTimeVersion = "0.4.0"
}



object Deps {

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }

    object Kermit {
        const val kermitMain = "co.touchlab:kermit:${Versions.kermit}"
    }


    object Coroutines {
        const val coroutinesNative = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
    }

    object Ktor {

        const val core = "io.ktor:ktor-client-core:${Versions.ktorVersion}"
        const val cio = "io.ktor:ktor-client-cio:$ktorVersion"
        const val serialization = "io.ktor:ktor-client-serialization:$ktorVersion"
        const val logging = "io.ktor:ktor-client-logging:$ktorVersion"
        const val content_negotiation = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
        const val json_serialization = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"



    }

    object KotlinX {
        const val serialization =  "org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion"
        const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion"

    }


}