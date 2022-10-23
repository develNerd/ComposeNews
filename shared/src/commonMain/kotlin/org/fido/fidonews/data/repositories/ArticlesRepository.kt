package org.fido.fidonews.data.repositories

import kotlinx.coroutines.flow.StateFlow
import org.fido.fidonews.data.model.ArticleResult
import org.fido.fidonews.data.network.OnResultObtained

interface ArticlesRepository {
    // TODO( --  StateFlow<OnResultObtained<T>> -- could be replaced by type alias  )
    suspend fun getArticlesByCountry(countryCode:String): StateFlowResult<ArticleResult>
}

typealias StateFlowResult<T> = StateFlow<OnResultObtained<T>>