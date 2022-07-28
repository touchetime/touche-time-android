package com.touchetime.presentation.ui.fragments.category

import androidx.lifecycle.ViewModel
import com.touchetime.presentation.model.CategorySelected
import com.touchetime.utils.state.CategoryState

class CategoryViewModel : ViewModel() {

    var categorySelected: CategoryState = CategoryState.DEFAULT
        private set

    fun getListCategory(): List<CategorySelected> {
        val listCategory = Category.getListCategory()

        listCategory.forEach {
            it.isSelected = it.enum == categorySelected
        }

        return listCategory
    }

    fun setupCategorySelected(categoryState: CategoryState) {
        this.categorySelected = categoryState
    }
}
