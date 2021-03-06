package com.touchetime.presentation.model

import android.os.Parcelable
import com.touchetime.utils.state.StyleState
import kotlinx.parcelize.Parcelize

@Parcelize
data class StyleSelected(
    var enum: StyleState,
    var isSelected: Boolean = false
) : Parcelable
