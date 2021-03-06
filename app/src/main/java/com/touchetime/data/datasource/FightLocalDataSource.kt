package com.touchetime.data.datasource

import com.touchetime.data.model.FightResponse
import kotlinx.coroutines.flow.Flow

interface FightLocalDataSource {
    fun getListFight(): Flow<List<FightResponse>>
    fun addFight(fight: FightResponse)
}
