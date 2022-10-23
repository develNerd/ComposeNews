package org.paypay.fidonews.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.paypay.fidonews.android.MainActivityViewModel

val presentationModule = module {
    viewModel { MainActivityViewModel() }
}