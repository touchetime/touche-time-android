package com.touchetime.presentation.ui.fragments.fight

import androidx.lifecycle.ViewModel

class FightViewModel : ViewModel() {

    var shouldStartFirstRound: Boolean = false
    var shouldStartSecondRound: Boolean = false
    var shouldStartInterval: Boolean = false
    var timerRound: Long? = null

    fun setupTimerRounder(value: Long) {
        this.timerRound = value
    }

    fun addScoreRed() {}
    fun removeScoreRed() {}
    fun setupToucheRed() {}
    fun setupFoulRed() {}

    fun addScoreBlue() {}
    fun removeScoreBlue() {}
    fun setupToucheBlue() {}
    fun setupFoulBlue() {}
}
