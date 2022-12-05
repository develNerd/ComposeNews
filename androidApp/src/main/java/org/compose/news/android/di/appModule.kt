package org.compose.news.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.compose.news.android.MainActivityViewModel

val presentationModule = module {
    viewModel { MainActivityViewModel() }
}