package com.touchetime.presentation.ui.fragments.fight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.touchetime.presentation.model.Athlete
import com.touchetime.presentation.state.AthleteState
import kotlinx.coroutines.launch

class FightViewModel : ViewModel() {

    var athleteRedUpdated = Athlete()
        private set
    var athleteBlueUpdated = Athlete()
        private set

    private val _athleteRed =
        MutableLiveData<AthleteState>(AthleteState.AthleteDefault(athleteRedUpdated))
    private val _athleteBlue =
        MutableLiveData<AthleteState>(AthleteState.AthleteDefault(athleteBlueUpdated))
    private val _fightName = MutableLiveData<String>()

    val athleteRed: LiveData<AthleteState> = _athleteRed
    val athleteBlue: LiveData<AthleteState> = _athleteBlue
    val fightName: LiveData<String> = _fightName

    var shouldStartFirstRound: Boolean = false
    var shouldStartSecondRound: Boolean = false
    var shouldStartInterval: Boolean = false
    var timerRound: Long? = null

    fun setupFightName(fightName: String) {
        viewModelScope.launch {
            _fightName.postValue(fightName)
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
}
