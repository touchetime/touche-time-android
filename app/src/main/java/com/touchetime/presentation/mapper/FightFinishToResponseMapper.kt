package com.touchetime.presentation.mapper

import com.touchetime.data.model.AthleteBlueResponse
import com.touchetime.data.model.AthleteRedResponse
import com.touchetime.data.model.FightResponse
import com.touchetime.presentation.model.AthleteFinish
import com.touchetime.presentation.model.FightFinish
import com.touchetime.utils.Mapper

class FightFinishToResponseMapper : Mapper<List<FightFinish>, List<FightResponse>> {
    override fun map(source: List<FightFinish>): List<FightResponse> {
        val listFightResponse = mutableListOf<FightResponse>()

        source.map {
            listFightResponse.add(
                FightResponse(
                    id = it.id,
                    name = it.name,
                    category = it.category,
                    style = it.style,
                    weight = it.weight,
                    rounds = it.rounds,
                    timeRound = it.timeRound,
                    timeInterval = it.timeInterval,
                    superiorityTechnical = it.superiorityTechnical,
                    athleteRed = getAthleteRedResponse(it.athleteRed),
                    athleteBlue = getAthleteBlueResponse(it.athleteBlue),
                    athleteWinner = it.athleteWinner,
                    isTouche = it.isTouche
                )
            )
        }

        return listFightResponse.toList()
    }

    private fun getAthleteRedResponse(source: AthleteFinish): AthleteRedResponse =
        AthleteRedResponse(
            colorRed = source.color,
            score = source.score,
            foul = source.foul
        )

    private fun getAthleteBlueResponse(source: AthleteFinish): AthleteBlueResponse =
        AthleteBlueResponse(
            colorBlue = source.color,
            score = source.score,
            foul = source.foul
        )
}
