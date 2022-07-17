package com.touchetime.presentation.ui.fragments.fight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.touchetime.presentation.model.Athlete
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.state.AthleteState
import com.touchetime.presentation.state.ColorState
import com.touchetime.presentation.state.ScoreState
import com.touchetime.presentation.state.StyleState
import kotlinx.coroutines.launch

class FightViewModel : ViewModel() {

    var athleteRedUpdated = Athlete(color = ColorState.RED)
        private set
    var athleteBlueUpdated = Athlete(color = ColorState.BLUE)
        private set

    private val _athleteRed =
        MutableLiveData<AthleteState>(AthleteState.AthleteDefault(athleteRedUpdated))
    private val _athleteBlue =
        MutableLiveData<AthleteState>(AthleteState.AthleteDefault(athleteBlueUpdated))
    private val _fight = MutableLiveData<Fight>()

    val athleteRed: LiveData<AthleteState> = _athleteRed
    val athleteBlue: LiveData<AthleteState> = _athleteBlue
    val fight: LiveData<Fight> = _fight

    var shouldStartFirstRound: Boolean = false
    var shouldStartSecondRound: Boolean = false
    var shouldStartInterval: Boolean = false
    var timerRound: Long? = null

    fun resetFight() {
        resetAthleteRed()
        resetAthleteBlue()
    }

    private fun resetAthleteBlue() {
        athleteBlueUpdated.apply {
            score = 0
            foul = 0
            touche = false
        }
        _athleteBlue.value = AthleteState.AthleteDefault(athleteBlueUpdated)
    }

    private fun resetAthleteRed() {
        athleteRedUpdated.apply {
            score = 0
            foul = 0
            touche = false
        }
        _athleteRed.value = AthleteState.AthleteDefault(athleteRedUpdated)
    }

    fun setupFight(fight: Fight) {
        viewModelScope.launch {
            _fight.setValue(fight)
        }
    }

    fun setupTimerRounder(value: Long) {
        this.timerRound = value
    }

    fun setupAddScoreRed(scoreState: ScoreState) {
        athleteRedUpdated.score += scoreState.value

        _athleteRed.value = AthleteState.AthleteAddScore(
            athleteRedUpdated.score
        )

        checkIfAthleteWin(athleteRedUpdated, athleteBlueUpdated)
    }

    fun setupRemoveScoreRed() {
        if (athleteRedUpdated.score > 0) {
            --athleteRedUpdated.score

            _athleteRed.value = AthleteState.AthleteRemoveScore(
                athleteRedUpdated.score
            )

            checkIfAthleteWin(athleteRedUpdated, athleteBlueUpdated)
            checkIfAthleteWin(athleteBlueUpdated, athleteRedUpdated)
        }
    }

    fun setupToucheRed() {
        athleteRedUpdated.touche = true

        _athleteRed.value = AthleteState.AthleteAddTouche(true)
    }

    fun setupAddFoulRed() {
        ++athleteRedUpdated.foul

        _athleteRed.value = AthleteState.AthleteAddFoul(
            athleteRedUpdated.foul
        )
    }

    fun setupRemoveFoulRed() {
        if (athleteRedUpdated.foul > 0) {
            --athleteRedUpdated.foul

            _athleteRed.value = AthleteState.AthleteRemoveFoul(
                athleteRedUpdated.foul
            )
        }
    }

    fun setupAddScoreBlue(scoreState: ScoreState) {
        athleteBlueUpdated.score += scoreState.value

        _athleteBlue.value = AthleteState.AthleteAddScore(
            athleteBlueUpdated.score
        )

        checkIfAthleteWin(athleteBlueUpdated, athleteRedUpdated)
    }

    fun setupRemoveScoreBlue() {
        if (athleteBlueUpdated.score > 0) {
            --athleteBlueUpdated.score

            _athleteBlue.value = AthleteState.AthleteRemoveScore(
                athleteBlueUpdated.score
            )

            checkIfAthleteWin(athleteBlueUpdated, athleteRedUpdated)
            checkIfAthleteWin(athleteRedUpdated, athleteBlueUpdated)
        }
    }

    fun setupToucheBlue() {
        athleteBlueUpdated.touche = true

        _athleteBlue.value = AthleteState.AthleteAddTouche(true)
    }

    fun setupAddFoulBlue() {
        ++athleteBlueUpdated.foul

        _athleteBlue.value = AthleteState.AthleteAddFoul(
            athleteBlueUpdated.foul
        )
    }

    fun setupRemoveFoulBlue() {
        if (athleteBlueUpdated.foul > 0) {
            --athleteBlueUpdated.foul

            _athleteBlue.value = AthleteState.AthleteRemoveFoul(
                athleteBlueUpdated.foul
            )
        }
    }

    private fun checkIfAthleteWin(athleteWinner: Athlete, athleteLoser: Athlete) {
        if (checkTechnicalSuperiority(athleteWinner, athleteLoser)) {
            if (athleteWinner.color == athleteRedUpdated.color) {
                _athleteRed.setValue(
                    AthleteState.AthleteWin(true)
                )
            } else {
                _athleteBlue.setValue(
                    AthleteState.AthleteWin(true)
                )
            }
        }
    }

    private fun checkTechnicalSuperiority(athleteWinner: Athlete, athleteLoser: Athlete): Boolean =
        if (isGrecoRoman()) {
            athleteWinner.score - athleteLoser.score >= GRECO_ROMAN_SUPERIORITY
        } else {
            athleteWinner.score - athleteLoser.score >= FREE_STYLE_SUPERIORITY
        }

    private fun isGrecoRoman(): Boolean = _fight.value?.style == StyleState.GRECO_ROMAN

    companion object {
        private const val GRECO_ROMAN_SUPERIORITY = 8
        private const val FREE_STYLE_SUPERIORITY = 10
    }
}
