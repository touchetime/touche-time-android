package com.touchetime.presentation.ui.fragments.weight

import androidx.lifecycle.ViewModel
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.model.WeightSelect

class WeightViewModel : ViewModel() {

    var fight: Fight = Fight()
        private set

    fun getListWeight(): List<WeightSelect> {
        val listWeight = Weight.getListWeight(fight)

        listWeight.forEach {
            it.isSelected = it.value == fight.weight
        }

        return listWeight
    }

    fun setupFight(fight: Fight) {
        this.fight = fight
    }
}
