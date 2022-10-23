package org.paypay.fidonews.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.paypay.fidonews.data.repositories.ArticlesRepository
import org.paypay.fidonews.data.repositoryImpl.ArticlesRepositoryImpl

actual fun platformModule() : Module = module {
    single<ArticlesRepository> { ArticlesRepositoryImpl(get()) }
}
