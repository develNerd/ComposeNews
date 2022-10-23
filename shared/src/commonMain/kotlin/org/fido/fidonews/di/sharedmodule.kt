package org.fido.fidonews.di

import org.koin.dsl.module
import org.fido.fidonews.data.network.Api
import org.fido.fidonews.data.network.ApiClient

val sharedModule = module {
    single{ ApiClient() }
    single { Api(get()) }
}