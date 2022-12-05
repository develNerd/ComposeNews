package org.compose.news.data.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import org.compose.news.data.appBaseUrl

class ApiClient() {
    private val THIRTY_SECONDS = 30_000L // Equivalent to 30 Sec

    val httpClient
            = HttpClient {


        install(ContentNegotiation) {
            json()
        }

        request {
            timeout {
                requestTimeoutMillis = THIRTY_SECONDS
            }
        }


        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.v { message }
                }
            }
            level = LogLevel.ALL
        }
        defaultRequest {
            host = appBaseUrl
            header("Content-Type", "application/json")
            header("Accept", "application/json")
        }



        followRedirects = false

    }

    suspend inline fun <reified T : Any> GET(
        route: String,
        queryPair:List<Pair<String,String>>? = null,
    ): HttpResponse = httpClient.get {
        url{
            host = appBaseUrl
            protocol = URLProtocol.HTTPS
            path(route)
            queryPair?.forEach { pair ->
                parameters.append(pair.first,pair.second)
            }
        }
    }



}