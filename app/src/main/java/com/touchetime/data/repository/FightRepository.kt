package com.touchetime.data.repository

import com.touchetime.data.model.FightResponse
import kotlinx.coroutines.flow.Flow

interface FightRepository {
    fun getListFight(): Flow<List<FightResponse>>
    suspend fun addFight(fightResponse: FightResponse)
}
