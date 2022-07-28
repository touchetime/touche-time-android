package com.touchetime.presentation.mapper

import com.touchetime.data.model.AthleteBlueResponse
import com.touchetime.data.model.AthleteRedResponse
import com.touchetime.data.model.FightResponse
import com.touchetime.presentation.model.AthleteFinish
import com.touchetime.presentation.model.FightFinish
import com.touchetime.utils.Mapper

class FightResponseMapper : Mapper<List<FightResponse>, List<FightFinish>> {
    override fun map(source: List<FightResponse>): List<FightFinish> {
        val listFightFinish = mutableListOf<FightFinish>()

        source.map {
            listFightFinish.add(
                FightFinish(
                    id = it.id,
                    name = it.name,
                    category = it.category,
                    style = it.style,
                    weight = it.weight,
                    rounds = it.rounds,
                    timeRound = it.timeRound,
                    timeInterval = it.timeInterval,
                    superiorityTechnical = it.superiorityTechnical,
                    athleteRed = getAthleteRedDomain(it.athleteRed),
                    athleteBlue = getAthleteBlueDomain(it.athleteBlue),
                    athleteWinner = it.athleteWinner,
                    isTouche = it.isTouche
                )
            )
        }

        return listFightFinish.toList().reversed()
    }

    private fun getAthleteRedDomain(source: AthleteRedResponse): AthleteFinish =
        AthleteFinish(
            color = source.colorRed,
            score = source.score,
            foul = source.foul
        )

    private fun getAthleteBlueDomain(source: AthleteBlueResponse): AthleteFinish =
        AthleteFinish(
            color = source.colorBlue,
            score = source.score,
            foul = source.foul
        )
}
