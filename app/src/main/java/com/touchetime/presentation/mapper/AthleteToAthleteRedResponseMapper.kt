package com.touchetime.presentation.mapper

import com.touchetime.data.model.AthleteRedResponse
import com.touchetime.presentation.model.Athlete
import com.touchetime.utils.Mapper

class AthleteToAthleteRedResponseMapper : Mapper<Athlete, AthleteRedResponse> {
    override fun map(source: Athlete): AthleteRedResponse =
        AthleteRedResponse(
            colorRed = source.color,
            foul = source.foul,
            score = source.score
        )
}
