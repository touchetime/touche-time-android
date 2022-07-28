package com.touchetime.presentation.model

import androidx.annotation.StringRes
import com.touchetime.utils.state.ColorState

data class FightFinish(
    val id: String,
    val name: String,
    @StringRes val category: Int,
    @StringRes val style: Int,
    val weight: String,
    val rounds: Int,
    val superiorityTechnical: Int,
    val timeRound: Long,
    val timeInterval: Long,
    val athleteRed: AthleteFinish,
    val athleteBlue: AthleteFinish,
    val athleteWinner: ColorState,
    val isTouche: Boolean
)

data class AthleteFinish(
    val color: ColorState,
    val score: Int,
    val foul: Int
)
