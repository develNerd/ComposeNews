package org.compose.news.data.repositoryImpl

import kotlinx.coroutines.flow.asStateFlow
import org.compose.news.data.model.ArticleResult
import org.compose.news.data.network.Api
import org.compose.news.data.network.makeRequestToApi
import org.compose.news.data.repositories.ArticlesRepository


class ArticlesRepositoryImpl(private val api: Api):ArticlesRepository {

     override suspend fun getArticlesByCountry(countryCode: String)  =
         makeRequestToApi {
            api.getLatestRates(countryCode)
        }.toOnResultObtained<ArticleResult>().asStateFlow()

}





