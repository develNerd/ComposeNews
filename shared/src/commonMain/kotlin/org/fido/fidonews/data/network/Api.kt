package org.fido.fidonews.data.network

import io.ktor.client.call.*
import org.fido.fidonews.data.model.ArticleResult

class Api(private val apiClient: ApiClient) {

    suspend fun getLatestRates(countryCode:String): ArticleResult {
        return apiClient.GET<String>(TOP_HEADLINES_ROUTE, listOf(Pair(COUNTRY_QUERY,  countryCode),Pair(API_KEY_QUERY,  apiKey))).body()
    }

}