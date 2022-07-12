package com.touchetime.presentation.state

import com.touchetime.presentation.model.Athlete

sealed class AthleteState {
    data class AthleteDefault(val athlete: Athlete) : AthleteState()
    data class AthleteAddScore(val score: Int) : AthleteState()
    data class AthleteRemoveScore(val score: Int) : AthleteState()
    data class AthleteAddTouche(val touche: Boolean) : AthleteState()
    data class AthleteAddFoul(val foul: Int) : AthleteState()
    data class AthleteRemoveFoul(val foul: Int) : AthleteState()
}
