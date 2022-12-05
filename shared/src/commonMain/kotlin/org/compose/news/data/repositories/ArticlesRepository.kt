package org.compose.news.data.repositories

import kotlinx.coroutines.flow.StateFlow
import org.compose.news.data.model.ArticleResult
import org.compose.news.data.network.OnResultObtained

interface ArticlesRepository {
    // TODO( --  StateFlow<OnResultObtained<T>> -- could be replaced by type alias  )
    suspend fun getArticlesByCountry(countryCode:String): StateFlowResult<ArticleResult>
}

typealias StateFlowResult<T> = StateFlow<OnResultObtained<T>>