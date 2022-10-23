package org.paypay.fidonews.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.paypay.fidonews.data.model.ArticleResult
import org.paypay.fidonews.data.model.ArticlesItem
import org.paypay.fidonews.data.network.OnResultObtained
import org.paypay.fidonews.data.repositories.ArticlesRepository

class MainActivityViewModel : ViewModel(),KoinComponent {

    private val articlesRepository:ArticlesRepository by inject()

    private val _articlesByCountry =
        MutableStateFlow(OnResultObtained<ArticleResult>(null,false,null))
    val articlesByCountry: StateFlow<OnResultObtained<ArticleResult>>
        get() = _articlesByCountry

    fun getArticlesByCountry(countryCode:String){
        viewModelScope.launch {
            articlesRepository.getArticlesByCountry(countryCode).collectLatest {result ->
                _articlesByCountry.value = result
            }
        }
    }

}