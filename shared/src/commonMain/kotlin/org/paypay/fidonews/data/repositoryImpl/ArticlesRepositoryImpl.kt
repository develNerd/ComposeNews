package org.paypay.fidonews.data.repositoryImpl

import kotlinx.coroutines.flow.asStateFlow
import org.paypay.fidonews.data.model.ArticleResult
import org.paypay.fidonews.data.network.Api
import org.paypay.fidonews.data.network.makeRequestToApi
import org.paypay.fidonews.data.repositories.ArticlesRepository
import org.paypay.fidonews.data.repositories.StateFlowResult


class ArticlesRepositoryImpl(private val api: Api):ArticlesRepository {






     override suspend fun getArticlesByCountry(countryCode: String)  =
         makeRequestToApi {
            api.getLatestRates(countryCode)
        }.toOnResultObtained<ArticleResult>().asStateFlow()

}

