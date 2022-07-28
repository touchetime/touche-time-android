package com.touchetime.data.repository

import com.touchetime.data.datasource.FightLocalDataSource
import com.touchetime.data.model.FightResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FightRepositoryImpl(private val fightLocalDataSource: FightLocalDataSource) : FightRepository {

    override fun getListFight(): Flow<List<FightResponse>> =
        fightLocalDataSource.getListFight()

    override suspend fun addFight(fightResponse: FightResponse) {
        withContext(Dispatchers.IO) {
            fightLocalDataSource.addFight(fightResponse)
        }
    }
}
