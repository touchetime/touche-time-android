package com.touchetime.presentation.model

import android.os.Parcelable
import com.touchetime.Constants
import com.touchetime.presentation.state.CategoryState
import com.touchetime.presentation.state.StyleState
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fight(
    var nameFight: String? = null,
    var category: CategoryState = CategoryState.DEFAULT,
    var style: StyleState = StyleState.DEFAULT,
    var weight: String? = null,
    val isCustom: Boolean = false,
    var superiorityTechnical: Int = Constants.FREE_STYLE_SUPERIORITY,
    var numberRounds: Int = 2,
    var timeRound: Long = Constants.TIME_ROUND_TREE_MINUTES,
    var timeInterval: Long = Constants.TIME_INTERVAL
) : Parcelable
