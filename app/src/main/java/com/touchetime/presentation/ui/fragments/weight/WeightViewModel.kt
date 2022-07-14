package com.touchetime.presentation.ui.fragments.weight

import androidx.lifecycle.ViewModel
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.model.ObjectToSelect

class WeightViewModel : ViewModel() {

    var fight: Fight? = null
        private set

    fun getListWeight(): List<ObjectToSelect> {
        val listWeight = Weight.getListWeight(fight)

        listWeight.forEach {
            it.isSelected = it.params == fight?.weight
        }

        return listWeight
    }

    fun setupFight(fight: Fight?) {
        this.fight = fight
    }
}
