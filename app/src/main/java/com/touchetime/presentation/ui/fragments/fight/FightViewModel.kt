package com.touchetime.presentation.ui.fragments.fight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.touchetime.presentation.model.Athlete
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.model.Score
import com.touchetime.presentation.state.* // ktlint-disable no-wildcard-imports
import com.touchetime.presentation.util.Constants.FREE_STYLE_SUPERIORITY
import com.touchetime.presentation.util.Constants.GRECO_ROMAN_SUPERIORITY
import com.touchetime.presentation.util.Constants.TIME_ROUND_TREE_MINUTES
import com.touchetime.presentation.util.Constants.TIME_ROUND_TWO_MINUTES
import com.touchetime.presentation.util.getTimeChronometer
import kotlinx.coroutines.launch
import java.util.*

class FightViewModel : ViewModel() {

    private val scoreRedHistory = mutableListOf<Score>()
    private val scoreBlueHistory = mutableListOf<Score>()

    var athleteRedUpdated = Athlete(color = ColorState.RED)
        private set
    var athleteBlueUpdated = Athlete(color = ColorState.BLUE)
        private set

    private val _athleteRed =
        MutableLiveData<AthleteState>(AthleteState.AthleteDefault(athleteRedUpdated))
    private val _athleteBlue =
        MutableLiveData<AthleteState>(AthleteState.AthleteDefault(athleteBlueUpdated))
    private val _fight = MutableLiveData<Fight>()
    private val _time = MutableLiveData(getTimeChronometer(TIME_ROUND_TREE_MINUTES))
    private val _round = MutableLiveData(RoundState.ROUND_ONE)

    val athleteRed: LiveData<AthleteState> = _athleteRed
    val athleteBlue: LiveData<AthleteState> = _athleteBlue
    val fight: LiveData<Fight> = _fight
    val time: LiveData<String> = _time
    val round: LiveData<RoundState> = _round

    var shouldStartSecondRound: Boolean = false
        private set
    var shouldStartInterval: Boolean = false
        private set
    var timerRound: Long = TIME_ROUND_TREE_MINUTES
        private set

    fun resetFight() {
        resetAthleteRed()
        resetAthleteBlue()
        resetScoreList()
        resetChronometer()
    }

    fun finishFight() {
        if (athleteRedUpdated.score != athleteBlueUpdated.score) {
            if (athleteRedUpdated.score > athleteBlueUpdated.score) {
                _athleteRed.value = AthleteState.AthleteWin(true)
            } else {
                _athleteBlue.value = AthleteState.AthleteWin(true)
            }
        } else {
            if (athleteRedUpdated.foul > athleteBlueUpdated.foul) {
                _athleteRed.value = AthleteState.AthleteWin(true)
            } else if (athleteRedUpdated.foul < athleteBlueUpdated.foul) {
                _athleteBlue.value = AthleteState.AthleteWin(true)
            } else {
                checkWinnerForLastScore()
            }
        }
    }

    fun setupChronometer() {
        when (_fight.value?.category) {
            CategoryState.DEFAULT, CategoryState.U23, CategoryState.U20 -> {
                setupTimerRounder(TIME_ROUND_TREE_MINUTES)
                setupTimeChronometer(TIME_ROUND_TREE_MINUTES)
            }
            else -> {
                setupTimerRounder(TIME_ROUND_TWO_MINUTES)
                setupTimeChronometer(TIME_ROUND_TWO_MINUTES)
            }
        }
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

        scoreRedHistory.add(Score(Calendar.getInstance().time, scoreState.value))

        _athleteRed.value = AthleteState.AthleteAddScore(
            athleteRedUpdated.score
        )

        checkIfAthleteWin(athleteRedUpdated, athleteBlueUpdated)
    }

    fun setupRemoveScoreRed(scoreState: ScoreState) {
        if (athleteRedUpdated.score - scoreState.value >= 0) {
            athleteRedUpdated.score -= scoreState.value

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

        scoreBlueHistory.add(Score(Calendar.getInstance().time, scoreState.value))

        _athleteBlue.value = AthleteState.AthleteAddScore(
            athleteBlueUpdated.score
        )

        checkIfAthleteWin(athleteBlueUpdated, athleteRedUpdated)
    }

    fun setupRemoveScoreBlue(scoreState: ScoreState) {
        if (athleteBlueUpdated.score - scoreState.value >= 0) {
            athleteBlueUpdated.score -= scoreState.value

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

    fun setupTimeChronometer(value: Long) {
        _time.value = getTimeChronometer(value)
    }

    fun setupShouldStartInterval(value: Boolean) {
        shouldStartInterval = value
    }

    fun setupShouldStartSecondRound(value: Boolean) {
        shouldStartSecondRound = value
    }

    fun setupRound(roundState: RoundState) {
        _round.value = roundState
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

    private fun resetScoreList() {
        scoreRedHistory.clear()
        scoreBlueHistory.clear()
    }

    private fun resetChronometer() {
        setupShouldStartInterval(false)
        setupShouldStartSecondRound(false)
        setupRound(RoundState.ROUND_ONE)
        setupChronometer()
    }

    private fun checkTechnicalSuperiority(athleteWinner: Athlete, athleteLoser: Athlete): Boolean =
        if (isGrecoRoman()) {
            athleteWinner.score - athleteLoser.score >= GRECO_ROMAN_SUPERIORITY
        } else {
            athleteWinner.score - athleteLoser.score >= FREE_STYLE_SUPERIORITY
        }

    private fun isGrecoRoman(): Boolean = _fight.value?.style == StyleState.GRECO_ROMAN

    private fun checkWinnerForLastScore() {
        if (scoreRedHistory.isEmpty() && scoreRedHistory.isNotEmpty()) {
            _athleteBlue.value = AthleteState.AthleteWin(true)
        } else if (scoreRedHistory.isNotEmpty() && scoreBlueHistory.isEmpty()) {
            _athleteRed.value = AthleteState.AthleteWin(true)
        } else {
            val response = scoreRedHistory
                .takeIf { it.isNotEmpty() }
                ?.last()
                ?.time
                ?.compareTo(scoreBlueHistory.last().time)

            if (response != null) {
                if (response > 0) {
                    _athleteRed.value = AthleteState.AthleteWin(true)
                } else {
                    _athleteBlue.value = AthleteState.AthleteWin(true)
                }
            }
        }
    }
}
