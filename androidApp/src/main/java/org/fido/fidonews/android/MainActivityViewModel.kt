package org.fido.fidonews.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.fido.fidonews.data.model.ArticleResult
import org.fido.fidonews.data.model.SupportedCountries
import org.fido.fidonews.data.network.OnResultObtained
import org.fido.fidonews.data.repositories.ArticlesRepository

class MainActivityViewModel : ViewModel(),KoinComponent {

    private val articlesRepository:ArticlesRepository by inject()

    val articlesSupportedCountries = listOf(
        SupportedCountries("Israel","il"),
        SupportedCountries("Argentina","ar"),
        SupportedCountries("UAE","ae"),
        SupportedCountries("United States","us"),
    )

    private val _articlesByCountry =
        MutableStateFlow(OnResultObtained<ArticleResult>(null,false,null))
    val articlesByCountry: StateFlow<OnResultObtained<ArticleResult>>
        get() = _articlesByCountry

    fun getArticlesByCountry(countryCode:String){
        viewModelScope.launch {
            articlesRepository.getArticlesByCountry(countryCode).collectLatest {result ->
                Log.e("Result",result.result?.toString().toString())
                _articlesByCountry.value = result
            }
        }
    }

}