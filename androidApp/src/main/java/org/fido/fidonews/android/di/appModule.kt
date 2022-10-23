package org.fido.fidonews.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.fido.fidonews.android.MainActivityViewModel

val presentationModule = module {
    viewModel { MainActivityViewModel() }
}