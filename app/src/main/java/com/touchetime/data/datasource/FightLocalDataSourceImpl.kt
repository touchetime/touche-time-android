package com.touchetime.data.datasource

import com.touchetime.data.dao.ToucheTimeDao
import com.touchetime.data.model.FightResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FightLocalDataSourceImpl(
    private val dao: ToucheTimeDao
) : FightLocalDataSource {

    override fun getListFight(): Flow<List<FightResponse>> = flow {
        emit(dao.getListFight())
    }

    override fun addFight(fight: FightResponse) {
        dao.addFightFinish(fight)
    }
}
