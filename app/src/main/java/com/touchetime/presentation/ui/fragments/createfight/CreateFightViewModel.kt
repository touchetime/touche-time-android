package com.touchetime.presentation.ui.fragments.createfight

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.touchetime.Constants
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.state.CategoryState
import com.touchetime.presentation.state.StyleState
import kotlinx.coroutines.launch

class CreateFightViewModel : ViewModel() {

    var fight = Fight()
        private set

    private val _categorySelected = MutableLiveData<CategoryState>()
    private val _styleSelected = MutableLiveData<StyleState>()
    private val _weightSelected = MutableLiveData<String>()

    val categorySelected: LiveData<CategoryState> = _categorySelected
    val styleSelected: LiveData<StyleState> = _styleSelected
    val weightSelected: LiveData<String> = _weightSelected

    fun setupCategorySelected(categoryState: CategoryState) {
        viewModelScope.launch {
            _categorySelected.postValue(categoryState)
            fight.category = categoryState
            setupTimeRounds(categoryState)
        }
    }

    fun setupStyleSelected(styleSelected: StyleState) {
        viewModelScope.launch {
            _styleSelected.postValue(styleSelected)
            fight.style = styleSelected
        }
    }

    fun setupWeightSelected(weightSelected: String?) {
        viewModelScope.launch {
            _weightSelected.postValue(weightSelected)
            fight.weight = weightSelected
        }
    }

    fun setupNameFight(context: Context) {
        val nameFight =
            "${context.getString(fight.category.value)} | ${context.getString(fight.style.value)} ${fight.weight}"

        fight.nameFight = nameFight
    }

    private fun setupTimeRounds(categoryState: CategoryState) {
        when (categoryState) {
            CategoryState.U20, CategoryState.U23, CategoryState.SENIOR, CategoryState.MASTER, CategoryState.DEFAULT -> {
                fight.timeRound = Constants.TIME_ROUND_TREE_MINUTES
            }
            else -> {
                fight.timeRound = Constants.TIME_ROUND_TWO_MINUTES
            }
        }
    }
}
