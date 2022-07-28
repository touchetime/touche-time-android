package com.touchetime.data.repository

import com.touchetime.data.datasource.FightLocalDataSource
import com.touchetime.data.model.FightResponse
import kotlinx.coroutines.flow.Flow

class FightRepositoryImpl(private val fightLocalDataSource: FightLocalDataSource) :
    FightRepository {

    override fun getListFight(): Flow<List<FightResponse>> =
        fightLocalDataSource.getListFight()

    override fun addFight(fightResponse: FightResponse) =
        fightLocalDataSource.addFight(fightResponse)
}
