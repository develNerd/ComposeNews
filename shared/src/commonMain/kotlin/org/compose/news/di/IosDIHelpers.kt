package org.compose.news.di

import org.compose.news.data.usecasefactories.NewsUseCaseFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class IosDIHelpers() : KoinComponent {

    private val getArticlesUseCase: NewsUseCaseFactory by inject()

    fun getUseCase() = getArticlesUseCase
}