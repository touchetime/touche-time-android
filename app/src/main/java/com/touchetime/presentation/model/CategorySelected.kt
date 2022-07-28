package com.touchetime.presentation.model

import android.os.Parcelable
import com.touchetime.utils.state.CategoryState
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategorySelected(
    var enum: CategoryState,
    var isSelected: Boolean = false
) : Parcelable
