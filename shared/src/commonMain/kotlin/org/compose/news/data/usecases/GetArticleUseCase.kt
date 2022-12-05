package org.compose.news.data.usecases

import org.compose.news.data.model.ArticleResult
import org.compose.news.data.repositories.ArticlesRepository

class  GetArticlesUseCase(private val repository: ArticlesRepository): BaseUseCase<String, ArticleResult>() {
    override suspend fun dispatch(input: String) =  repository.getArticlesByCountry(input)
}
