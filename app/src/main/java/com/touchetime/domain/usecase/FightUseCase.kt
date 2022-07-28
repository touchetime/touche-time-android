package com.touchetime.domain.usecase

import com.touchetime.analytics.AnalyticsManager
import com.touchetime.analytics.FightEvent
import com.touchetime.data.model.FightResponse
import com.touchetime.data.repository.FightRepository
import kotlinx.coroutines.flow.Flow

class FightUseCase(private val fightRepository: FightRepository) {

    fun getListFight(): Flow<List<FightResponse>> =
        fightRepository.getListFight()

    fun addFight(fightResponse: FightResponse) {
        AnalyticsManager.sendEvent(
            FightEvent.FightCreated(
                fightResponse
            )
        )

        fightRepository.addFight(fightResponse)
    }
}
