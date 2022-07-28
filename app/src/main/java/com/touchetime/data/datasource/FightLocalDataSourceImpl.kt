package com.touchetime.data.datasource

import com.touchetime.data.model.FightResponse
import kotlinx.coroutines.flow.Flow

class FightLocalDataSourceImpl : FightLocalDataSource {

    override fun getListFight(): Flow<List<FightResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFight(fightResponse: FightResponse) {
        TODO("Not yet implemented")
    }
}
