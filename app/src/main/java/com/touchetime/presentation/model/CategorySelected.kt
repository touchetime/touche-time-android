package com.touchetime.presentation.model

import com.touchetime.presentation.state.CategoryState

data class CategorySelected(
    var enum: CategoryState,
    var isSelected: Boolean = false
)
