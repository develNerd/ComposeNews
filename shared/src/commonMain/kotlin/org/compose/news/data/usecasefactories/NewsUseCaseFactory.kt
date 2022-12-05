package org.compose.news.data.usecasefactories

import org.compose.news.data.repositories.ArticlesRepository
import org.compose.news.data.usecases.GetArticlesUseCase

class NewsUseCaseFactory(private val repository: ArticlesRepository){
    val getArticlesUseCase = GetArticlesUseCase(repository)
}