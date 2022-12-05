package org.compose.news.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.compose.news.data.repositories.ArticlesRepository
import org.compose.news.data.repositoryImpl.ArticlesRepositoryImpl
import org.compose.news.data.repositoryImpl.GetArticlesUseCase


actual fun platformModule() : Module = module {
    single<ArticlesRepository> { ArticlesRepositoryImpl(get()) }
}

actual fun useCaseModule(): Module = module {
    single { GetArticlesUseCase(get()) }
}