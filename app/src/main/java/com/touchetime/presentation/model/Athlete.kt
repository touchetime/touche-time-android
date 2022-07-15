package com.touchetime.presentation.model

import androidx.annotation.StringRes

data class Athlete(
    @StringRes val color: Int,
    var score: Int = 0,
    var foul: Int = 0,
    var touche: Boolean = false
)
