package com.touchetime.presentation.model

import android.os.Parcelable
import com.touchetime.presentation.state.CategoryState
import com.touchetime.presentation.state.StyleState
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fight(
    var nameFight: String? = null,
    var category: CategoryState = CategoryState.DEFAULT,
    var style: StyleState = StyleState.DEFAULT,
    var weight: String? = null
) : Parcelable
