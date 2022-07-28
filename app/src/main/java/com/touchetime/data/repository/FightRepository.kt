package com.touchetime.data.repository

import com.touchetime.data.model.FightResponse
import kotlinx.coroutines.flow.Flow

interface FightRepository {
    fun getListFight(): Flow<List<FightResponse>>
    fun addFight(fightResponse: FightResponse)
}
