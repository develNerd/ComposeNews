package org.paypay.fidonews.di

import kotlinx.coroutines.MainScope
import org.koin.dsl.module
import org.paypay.fidonews.data.network.Api
import org.paypay.fidonews.data.network.ApiClient

val sharedModule = module {
    single{ ApiClient() }
    single { Api(get()) }
}