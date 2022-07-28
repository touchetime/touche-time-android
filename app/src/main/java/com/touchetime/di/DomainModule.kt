package com.touchetime.di

import com.touchetime.domain.usecase.FightUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FightUseCase(fightRepository = get()) }
}
