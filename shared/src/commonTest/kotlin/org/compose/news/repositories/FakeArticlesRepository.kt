package org.compose.news.repositories

import kotlinx.coroutines.flow.MutableStateFlow
import org.compose.news.data.model.ArticleResult
import org.compose.news.data.network.OnResultObtained
import org.compose.news.data.repositories.ArticlesRepository
import org.compose.news.data.repositories.StateFlowResult

class FakeArticlesRepository(private val articleResult:ArticleResult, private val isLoaded:Boolean = true,
                             private val error: Exception? = null) : ArticlesRepository {
    override suspend fun getArticlesByCountry(countryCode: String): StateFlowResult<ArticleResult> {
        return MutableStateFlow(OnResultObtained(articleResult,isLoaded,error))
    }
}