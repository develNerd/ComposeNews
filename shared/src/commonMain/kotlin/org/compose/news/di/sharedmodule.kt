package org.compose.news.di

import org.koin.dsl.module
import org.compose.news.data.network.Api
import org.compose.news.data.network.ApiClient

val sharedModule = module {
    single{ ApiClient() }
    single { Api(get()) }
}

