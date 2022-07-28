package com.touchetime.di

import com.touchetime.data.appdatabase.AppDatabase
import com.touchetime.data.datasource.FightLocalDataSource
import com.touchetime.data.datasource.FightLocalDataSourceImpl
import com.touchetime.data.repository.FightRepository
import com.touchetime.data.repository.FightRepositoryImpl
import com.touchetime.domain.usecase.FightUseCase
import com.touchetime.presentation.ui.fragments.fight.FightViewModel
import com.touchetime.presentation.ui.fragments.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        HomeViewModel(
            fightUseCase = FightUseCase(
                fightRepository = get()
            )
        )
    }

    viewModel {
        FightViewModel(
            fightUseCase = FightUseCase(
                fightRepository = get()
            )
        )
    }
}

val dataModule = module {
    factory<FightRepository> { FightRepositoryImpl(fightLocalDataSource = get()) }
    factory<FightLocalDataSource> { FightLocalDataSourceImpl(dao = get()) }
    single { AppDatabase.getDatabase(androidContext()).toucheTimeDao }
}
