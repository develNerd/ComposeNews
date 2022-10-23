package org.paypay.fidonews.data.network

import io.ktor.client.call.*
import io.ktor.client.statement.*
import org.paypay.fidonews.data.model.ArticleResult

class Api(private val apiClient: ApiClient) {

    suspend fun getLatestRates(countryCode:String): ArticleResult {
        return apiClient.GET<String>(TOP_HEADLINES_ROUTE, listOf(Pair(COUNTRY_QUERY,  countryCode))).body()
    }

}