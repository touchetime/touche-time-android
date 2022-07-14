package com.touchetime.presentation.ui.fragments.customizefight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CustomizeFightViewModel : ViewModel() {

    private val _categorySelected = MutableLiveData<Int>()
    private val _styleSelected = MutableLiveData<Int>()
    private val _weightSelected = MutableLiveData<Int>()

    val categorySelected: LiveData<Int> = _categorySelected
    val styleSelected: LiveData<Int> = _styleSelected
    val weightSelected: LiveData<Int> = _weightSelected

    fun setupCategorySelected(categorySelected: Int) {
        viewModelScope.launch {
            _categorySelected.postValue(categorySelected)
        }
    }

    fun setupStyleSelected(styleSelected: Int) {
        viewModelScope.launch {
            _styleSelected.postValue(styleSelected)
        }
    }

    fun setupWeightSelected(weightSelected: Int?) {
        viewModelScope.launch {
            _weightSelected.postValue(weightSelected)
        }
    }
}
