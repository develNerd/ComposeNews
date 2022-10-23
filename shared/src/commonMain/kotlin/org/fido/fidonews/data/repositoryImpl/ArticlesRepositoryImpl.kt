package org.fido.fidonews.data.repositoryImpl

import kotlinx.coroutines.flow.asStateFlow
import org.fido.fidonews.data.model.ArticleResult
import org.fido.fidonews.data.network.Api
import org.fido.fidonews.data.network.makeRequestToApi
import org.fido.fidonews.data.repositories.ArticlesRepository


class ArticlesRepositoryImpl(private val api: Api):ArticlesRepository {






     override suspend fun getArticlesByCountry(countryCode: String)  =
         makeRequestToApi {
            api.getLatestRates(countryCode)
        }.toOnResultObtained<ArticleResult>().asStateFlow()

}

