package com.touchetime.presentation.ui.fragments.fight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.touchetime.analytics.AnalyticsManager
import com.touchetime.analytics.FightEvent
import com.touchetime.data.model.FightResponse
import com.touchetime.domain.usecase.FightUseCase
import com.touchetime.presentation.mapper.AthleteToAthleteBlueResponseMapper
import com.touchetime.presentation.mapper.AthleteToAthleteRedResponseMapper
import com.touchetime.presentation.model.Athlete
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.model.Score
import com.touchetime.presentation.state.AthleteState
import com.touchetime.utils.Constants.TIME_ROUND_TREE_MINUTES
import com.touchetime.utils.state.CategoryState
import com.touchetime.utils.state.ColorState
import com.touchetime.utils.state.RoundState
import com.touchetime.utils.state.ScoreState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class FightViewModel(
    private val fightUseCase: FightUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val scoreRedHistory = mutableListOf<Score>()
    private val scoreBlueHistory = mutableListOf<Score>()

    var athleteRedUpdated = Athlete(color = ColorState.RED)
        private set
    var athleteBlueUpdated = Athlete(color = ColorState.BLUE)
        private set
    var roundIsFinished: Boolean = true
        private set
    var timerRound: Long = TIME_ROUND_TREE_MINUTES
        private set
    var currentRound: Int = 1
        private set

    private val _athleteRed =
        MutableLiveData<AthleteState>(AthleteState.AthleteDefault(athleteRedUpdated))
    private val _athleteBlue =
        MutableLiveData<AthleteState>(AthleteState.AthleteDefault(athleteBlueUpdated))
    private val _fight = MutableLiveData<Fight>()
    private val _time = MutableLiveData(TIME_ROUND_TREE_MINUTES)
    private val _round = MutableLiveData(RoundState.ROUND)

    val athleteRed: LiveData<AthleteState> = _athleteRed
    val athleteBlue: LiveData<AthleteState> = _athleteBlue
    val fight: LiveData<Fight> = _fight
    val time: LiveData<Long> = _time
    val round: LiveData<RoundState> = _round

    fun sendEvent(fight: Fight) {
        AnalyticsManager.sendEvent(
            FightEvent.FightOpened(fight)
        )
    }

    fun resetFight() {
        resetAthleteRed()
        resetAthleteBlue()
        resetScoreList()
        resetChronometer()
        currentRound = 1
    }

    fun checkFinishFight() {
        if (athleteRedUpdated.score != athleteBlueUpdated.score) {
            if (athleteRedUpdated.score > athleteBlueUpdated.score) {
                setupAthleteRedWinner()
            } else {
                setupAthleteBlueWinner()
            }
        } else {
            if (athleteRedUpdated.foul > athleteBlueUpdated.foul) {
                setupAthleteRedWinner()
            } else if (athleteRedUpdated.foul < athleteBlueUpdated.foul) {
                setupAthleteBlueWinner()
            } else {
                checkWinnerForLastScore()
            }
        }
    }

    fun updateFight(fight: Fight) {
        _fight.value = fight
        resetFight()
    }

    fun setupChronometerToRound() {
        when (_fight.value?.category) {
            CategoryState.SENIOR, CategoryState.U23, CategoryState.U20, CategoryState.MASTER -> {
                setupTimerRounder(TIME_ROUND_TREE_MINUTES)
                _fight.value?.timeRound?.let { setupTimeChronometer(it) }
            }
            CategoryState.DEFAULT -> {
                _fight.value?.let {
                    setupTimerRounder(it.timeRound)
                    setupTimeChronometer(it.timeRound)
                }
            }
            else -> {
                _fight.value?.let {
                    setupTimerRounder(it.timeRound)
                    setupTimeChronometer(it.timeRound)
                }
            }
        }
    }

    fun setupFight(fight: Fight) {
        _fight.value = fight
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
        setupAddScoreBlue(ScoreState.ONE)

        _athleteRed.value = AthleteState.AthleteAddFoul(
            athleteRedUpdated.foul
        )

        if (athleteRedUpdated.foul == 3) {
            setupAthleteBlueWinner()
        }
    }

    fun setupRemoveFoulRed() {
        if (athleteRedUpdated.foul > 0) {
            --athleteRedUpdated.foul
            setupRemoveScoreBlue(ScoreState.ONE)

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
        setupAddScoreRed(ScoreState.ONE)

        _athleteBlue.value = AthleteState.AthleteAddFoul(
            athleteBlueUpdated.foul
        )

        if (athleteBlueUpdated.foul == 3) {
            setupAthleteRedWinner()
        }
    }

    fun setupRemoveFoulBlue() {
        if (athleteBlueUpdated.foul > 0) {
            --athleteBlueUpdated.foul
            setupRemoveScoreRed(ScoreState.ONE)

            _athleteBlue.value = AthleteState.AthleteRemoveFoul(
                athleteBlueUpdated.foul
            )
        }
    }

    fun setupTimeChronometer(value: Long) {
        _time.value = value
    }

    fun setupRound(roundState: RoundState) {
        _round.value = roundState
    }

    fun setupIsInRound(value: Boolean) {
        roundIsFinished = value
    }

    fun updateCurrentRound() {
        ++currentRound
    }

    fun addFinishFight(athleteWinner: Athlete) {
        viewModelScope.launch(dispatcher) {
            _fight.value?.let {
                val fightResponse = FightResponse(
                    name = it.nameFight ?: "",
                    category = it.category.value,
                    style = it.style.value,
                    weight = it.weight ?: "",
                    rounds = it.numberRounds,
                    timeRound = it.timeRound,
                    timeInterval = it.timeInterval,
                    superiorityTechnical = it.superiorityTechnical,
                    athleteRed = AthleteToAthleteRedResponseMapper().map(athleteRedUpdated),
                    athleteBlue = AthleteToAthleteBlueResponseMapper().map(athleteBlueUpdated),
                    athleteWinner = athleteWinner.color,
                    isTouche = if (athleteWinner.color == ColorState.RED) {
                        athleteRedUpdated.touche
                    } else {
                        athleteBlueUpdated.touche
                    }
                )

                fightUseCase.addFight(fightResponse)
            }
        }
    }

    private fun checkIfAthleteWin(athleteWinner: Athlete, athleteLoser: Athlete) {
        if (checkTechnicalSuperiority(athleteWinner, athleteLoser)) {
            if (athleteWinner.color == athleteRedUpdated.color) {
                setupAthleteRedWinner()
            } else {
                _athleteBlue.setValue(
                    AthleteState.AthleteWin(true)
                )
            }
        }
    }

    private fun setupAthleteRedWinner() {
        _athleteRed.value = AthleteState.AthleteWin(true)
    }

    private fun setupAthleteBlueWinner() {
        _athleteBlue.value = AthleteState.AthleteWin(true)
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
        setupIsInRound(false)
        setupRound(RoundState.ROUND)
        setupChronometerToRound()
    }

    private fun checkTechnicalSuperiority(athleteWinner: Athlete, athleteLoser: Athlete): Boolean {
        return _fight.value?.let {
            athleteWinner.score - athleteLoser.score >= it.superiorityTechnical
        } ?: false
    }

    private fun checkWinnerForLastScore() {
        if (scoreRedHistory.isEmpty() && scoreRedHistory.isNotEmpty()) {
            setupAthleteBlueWinner()
        } else if (scoreRedHistory.isNotEmpty() && scoreBlueHistory.isEmpty()) {
            setupAthleteRedWinner()
        } else {
            val response = scoreRedHistory
                .takeIf { it.isNotEmpty() }
                ?.last()
                ?.time
                ?.compareTo(scoreBlueHistory.last().time)

            if (response != null) {
                if (response > 0) {
                    setupAthleteRedWinner()
                } else {
                    setupAthleteBlueWinner()
                }
            }
        }
    }
}
