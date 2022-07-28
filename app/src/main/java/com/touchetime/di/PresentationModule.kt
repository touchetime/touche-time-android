package com.touchetime.di

import com.touchetime.presentation.ui.fragments.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        HomeViewModel(fightUseCase = get())
    }
}
