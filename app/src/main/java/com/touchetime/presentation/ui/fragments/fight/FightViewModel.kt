package com.touchetime.presentation.ui.fragments.fight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.touchetime.R
import com.touchetime.presentation.model.Athlete
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.state.AthleteState
import kotlinx.coroutines.launch

class FightViewModel : ViewModel() {

    var athleteRedUpdated = Athlete(color = R.string.red)
        private set
    var athleteBlueUpdated = Athlete(color = R.string.blue)
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

    fun setupFight(fight: Fight) {
        viewModelScope.launch {
            _fight.postValue(fight)
        }
    }

    fun setupTimerRounder(value: Long) {
        this.timerRound = value
    }

    fun setupAddScoreRed() {
        viewModelScope.launch {
            ++athleteRedUpdated.score

            _athleteRed.postValue(
                AthleteState.AthleteAddScore(
                    athleteRedUpdated.score
                )
            )

            checkIfAthleteWin(athleteRedUpdated, athleteBlueUpdated)
        }
    }

    fun setupRemoveScoreRed() {
        viewModelScope.launch {
            if (athleteRedUpdated.score > 0) {
                --athleteRedUpdated.score

                _athleteRed.postValue(
                    AthleteState.AthleteRemoveScore(
                        athleteRedUpdated.score
                    )
                )

                checkIfAthleteWin(athleteRedUpdated, athleteBlueUpdated)
                checkIfAthleteWin(athleteBlueUpdated, athleteRedUpdated)
            }
        }
    }

    fun setupToucheRed() {
        viewModelScope.launch {
            athleteRedUpdated.touche = true

            _athleteRed.postValue(
                AthleteState.AthleteAddTouche(true)
            )
        }
    }

    fun setupAddFoulRed() {
        viewModelScope.launch {
            ++athleteRedUpdated.foul

            _athleteRed.postValue(
                AthleteState.AthleteAddFoul(
                    athleteRedUpdated.foul
                )
            )
        }
    }

    fun setupRemoveFoulRed() {
        viewModelScope.launch {
            if (athleteRedUpdated.foul > 0) {
                --athleteRedUpdated.foul

                _athleteRed.postValue(
                    AthleteState.AthleteRemoveFoul(
                        athleteRedUpdated.foul
                    )
                )
            }
        }
    }

    fun setupAddScoreBlue() {
        viewModelScope.launch {
            ++athleteBlueUpdated.score

            _athleteBlue.postValue(
                AthleteState.AthleteAddScore(
                    athleteBlueUpdated.score
                )
            )

            checkIfAthleteWin(athleteBlueUpdated, athleteRedUpdated)
        }
    }

    fun setupRemoveScoreBlue() {
        viewModelScope.launch {
            if (athleteBlueUpdated.score > 0) {
                --athleteBlueUpdated.score

                _athleteBlue.postValue(
                    AthleteState.AthleteRemoveScore(
                        athleteBlueUpdated.score
                    )
                )

                checkIfAthleteWin(athleteBlueUpdated, athleteRedUpdated)
                checkIfAthleteWin(athleteRedUpdated, athleteBlueUpdated)
            }
        }
    }

    fun setupToucheBlue() {
        viewModelScope.launch {
            athleteBlueUpdated.touche = true

            _athleteBlue.postValue(
                AthleteState.AthleteAddTouche(true)
            )
        }
    }

    fun setupAddFoulBlue() {
        viewModelScope.launch {
            ++athleteBlueUpdated.foul

            _athleteBlue.postValue(
                AthleteState.AthleteAddFoul(
                    athleteBlueUpdated.foul
                )
            )
        }
    }

    fun setupRemoveFoulBlue() {
        viewModelScope.launch {
            if (athleteBlueUpdated.foul > 0) {
                --athleteBlueUpdated.foul

                _athleteBlue.postValue(
                    AthleteState.AthleteRemoveFoul(
                        athleteBlueUpdated.foul
                    )
                )
            }
        }
    }

    private fun checkIfAthleteWin(athleteWinner: Athlete, athleteLoser: Athlete) {
        if (checkTechnicalSuperiority(athleteWinner, athleteLoser)) {
            if (athleteWinner.color == athleteRedUpdated.color) {
                _athleteRed.postValue(
                    AthleteState.AthleteWin(true)
                )
            } else {
                _athleteBlue.postValue(
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

    private fun isGrecoRoman(): Boolean = _fight.value?.style == R.string.greco_roman

    companion object {
        private const val GRECO_ROMAN_SUPERIORITY = 8
        private const val FREE_STYLE_SUPERIORITY = 10
    }
}
