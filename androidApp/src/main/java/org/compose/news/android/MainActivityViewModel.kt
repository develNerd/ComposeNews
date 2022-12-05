package org.compose.news.android

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.compose.news.android.core.BaseViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.compose.news.data.model.ArticleResult
import org.compose.news.data.model.ArticlesItem
import org.compose.news.data.model.SupportedCountries
import org.compose.news.data.usecasefactories.NewsUseCaseFactory

class MainActivityViewModel : BaseViewModel(), KoinComponent {

    private val newsUseCaseFactory: NewsUseCaseFactory by inject()

    val articlesSupportedCountries = listOf(
        SupportedCountries("Israel", "il"),
        SupportedCountries("Argentina", "ar"),
        SupportedCountries("UAE", "ae"),
        SupportedCountries("United States", "us"),
    )

    private val _articlesByCountry =
        MutableStateFlow(UIResult<ArticleResult>(null, false, null))
    val articlesByCountry: StateFlow<UIResult<ArticleResult>>
        get() = _articlesByCountry

    private val _currentNewsItem = MutableStateFlow(ArticlesItem())
    val currentNewsItem: StateFlow<ArticlesItem>
        get() = _currentNewsItem

    fun setCurrentNewsItem(articlesItem: ArticlesItem){
        _currentNewsItem.value = articlesItem
    }

    fun getArticlesByCountry(countryCode: String) {
        executeApiCallUseCase(viewModelScope, countryCode, newsUseCaseFactory.getArticlesUseCase
        ) { result ->
            Log.e("Result",result.result?.toString() ?: "")
            _articlesByCountry.value = result
        }
    }

}