package com.touchetime.presentation.model

import android.os.Parcelable
import androidx.annotation.StringRes
import com.touchetime.presentation.state.CategoryState
import com.touchetime.presentation.state.StyleState
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fight(
    var nameFight: String? = null,
    var category: CategoryState? = null,
    var style: StyleState? = null,
    var weight: Int? = null
) : Parcelable
