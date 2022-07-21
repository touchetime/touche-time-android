package com.touchetime.presentation.ui.fragments.customizefight

import androidx.lifecycle.ViewModel
import com.touchetime.presentation.model.Fight

class CustomizeFightViewModel : ViewModel() {

    var fight = Fight()
        private set

    fun setupFight(fight: Fight) {
        this.fight = fight
    }
}
