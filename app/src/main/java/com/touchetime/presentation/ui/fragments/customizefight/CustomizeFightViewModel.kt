package com.touchetime.presentation.ui.fragments.customizefight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CustomizeFightViewModel : ViewModel() {

    private val _categorySelected = MutableLiveData<Int>()

    val categorySelected: LiveData<Int> = _categorySelected

    fun setupCategorySelected(categorySelected: Int) {
        viewModelScope.launch {
            _categorySelected.postValue(categorySelected)
        }
    }
}
