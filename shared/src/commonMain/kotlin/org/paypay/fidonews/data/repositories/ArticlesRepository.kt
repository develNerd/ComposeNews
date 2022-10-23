package org.paypay.fidonews.data.repositories

import kotlinx.coroutines.flow.StateFlow
import org.paypay.fidonews.data.model.ArticleResult
import org.paypay.fidonews.data.model.ArticlesItem
import org.paypay.fidonews.data.network.ApiResult
import org.paypay.fidonews.data.network.OnResultObtained

interface ArticlesRepository {
    // TODO( --  StateFlow<OnResultObtained<T>> -- could be replaced by type alias  )
    suspend fun countryArticles(countryCode:String): StateFlowResult<ArticleResult>
}

typealias StateFlowResult<T> = StateFlow<OnResultObtained<T>>