package com.touchetime.presentation.ui.fragments.category

import androidx.lifecycle.ViewModel
import com.touchetime.presentation.model.ObjectToSelect

class CategoryViewModel : ViewModel() {

    var categorySelected: Int? = null
        private set

    fun getListCategory(): List<ObjectToSelect> {
        val listCategory = Category.getListCategory()

        listCategory.forEach {
            it.isSelected = it.params == categorySelected
        }

        return listCategory
    }

    fun setupCategorySelected(categorySelected: Int?) {
        this.categorySelected = categorySelected
    }
}
