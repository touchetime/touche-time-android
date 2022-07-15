package com.touchetime.presentation.ui.fragments.customizefight

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.state.CategoryState
import kotlinx.coroutines.launch

class CustomizeFightViewModel : ViewModel() {

    var fight = Fight()
        private set

    private val _categorySelected = MutableLiveData<CategoryState>()
    private val _styleSelected = MutableLiveData<Int>()
    private val _weightSelected = MutableLiveData<Int>()

    val categorySelected: LiveData<CategoryState> = _categorySelected
    val styleSelected: LiveData<Int> = _styleSelected
    val weightSelected: LiveData<Int> = _weightSelected

    fun setupCategorySelected(categoryState: CategoryState) {
        viewModelScope.launch {
            _categorySelected.postValue(categoryState)
            fight.category = categoryState
        }
    }

    fun setupStyleSelected(styleSelected: Int) {
        viewModelScope.launch {
            _styleSelected.postValue(styleSelected)
            fight.style = styleSelected
        }
    }

    fun setupWeightSelected(weightSelected: Int?) {
        viewModelScope.launch {
            _weightSelected.postValue(weightSelected)
            fight.weight = weightSelected
        }
    }

    fun setupNameFight(context: Context) {
        val nameFight = "${fight.category?.let { context.getString(it.value) }} | ${
        fight.style?.let {
            context.getString(it)
        }
        } -${fight.weight}kg"

        fight.nameFight = nameFight
    }
}
