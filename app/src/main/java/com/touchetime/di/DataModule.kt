package com.touchetime.di

import com.touchetime.data.datasource.FightLocalDataSource
import com.touchetime.data.datasource.FightLocalDataSourceImpl
import com.touchetime.data.repository.FightRepository
import com.touchetime.data.repository.FightRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    factory<FightRepository> { FightRepositoryImpl(fightLocalDataSource = get()) }
    factory<FightLocalDataSource> { FightLocalDataSourceImpl() }
}
