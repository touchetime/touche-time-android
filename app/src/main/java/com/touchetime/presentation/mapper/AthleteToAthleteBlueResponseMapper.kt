package com.touchetime.presentation.mapper

import com.touchetime.data.model.AthleteBlueResponse
import com.touchetime.presentation.model.Athlete
import com.touchetime.utils.Mapper

class AthleteToAthleteBlueResponseMapper : Mapper<Athlete, AthleteBlueResponse> {
    override fun map(source: Athlete): AthleteBlueResponse =
        AthleteBlueResponse(
            colorBlue = source.color,
            foul = source.foul,
            score = source.score
        )
}
