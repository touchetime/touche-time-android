package com.touchetime.presentation.ui.fragments.category

import com.touchetime.presentation.model.CategorySelected
import com.touchetime.presentation.state.CategoryState

object Category {

    private val listCategory: List<CategorySelected> = listOf(
        CategorySelected(CategoryState.valueOf("CHILDREN_1")),
        CategorySelected(CategoryState.valueOf("CHILDREN_2")),
        CategorySelected(CategoryState.valueOf("CHILDREN_3")),
        CategorySelected(CategoryState.valueOf("U15")),
        CategorySelected(CategoryState.valueOf("U17")),
        CategorySelected(CategoryState.valueOf("U20")),
        CategorySelected(CategoryState.valueOf("U23")),
        CategorySelected(CategoryState.valueOf("U23")),
        CategorySelected(CategoryState.valueOf("MASTER")),
    )

    fun getListCategory(): List<CategorySelected> = listCategory
}
