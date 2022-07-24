package com.touchetime.presentation.ui.fragments.customizefight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.touchetime.Constants
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.state.TimeState

class CustomizeFightViewModel : ViewModel() {

    private val _fight = MutableLiveData<Fight>()

    var fight: LiveData<Fight> = _fight
        private set

    fun setupFight(fight: Fight) {
        _fight.value = fight
    }

    fun removeSuperiorityTechnical() {
        _fight.value?.let {
            var superiorityTechnical = it.superiorityTechnical

            if (superiorityTechnical > 1) {
                _fight.value = it.copy(superiorityTechnical = --superiorityTechnical)
            }
        }
    }

    fun addSuperiorityTechnical() {
        _fight.value = _fight.value?.let {
            it.copy(superiorityTechnical = it.superiorityTechnical + 1)
        }
    }

    fun removeRounds() {
        _fight.value?.let {
            var rounds = it.numberRounds

            if (rounds > 1) {
                _fight.value = it.copy(numberRounds = --rounds)
            }
        }
    }

    fun addRounds() {
        _fight.value = _fight.value?.let {
            it.copy(numberRounds = it.numberRounds + 1)
        }
    }

    fun removeTimeRound(timeState: TimeState) {
        _fight.value?.let {
            val timeRound = it.timeRound
            val timeUpdated = timeRound - timeState.value
            val shouldUpdate = timeUpdated >= Constants.TEN_SECONDS

            if (shouldUpdate) {
                _fight.value = it.copy(timeRound = timeUpdated)
            }
        }
    }

    fun addTimeRound(timeState: TimeState) {
        _fight.value = _fight.value?.let {
            it.copy(timeRound = it.timeRound + timeState.value)
        }
    }

    fun removeTimeInterval(timeState: TimeState) {
        _fight.value?.let {
            val timeRound = it.timeInterval
            val timeUpdated = timeRound - timeState.value
            val shouldUpdate = timeUpdated >= Constants.TEN_SECONDS

            if (shouldUpdate) {
                _fight.value = it.copy(timeInterval = timeUpdated)
            }
        }
    }

    fun addTimeInterval(timeState: TimeState) {
        _fight.value = _fight.value?.let {
            it.copy(timeInterval = it.timeInterval + timeState.value)
        }
    }
}
