package org.fido.fidonews.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.fido.fidonews.data.repositories.ArticlesRepository
import org.fido.fidonews.data.repositoryImpl.ArticlesRepositoryImpl

actual fun platformModule() : Module = module {
    single<ArticlesRepository> { ArticlesRepositoryImpl(get()) }
}
