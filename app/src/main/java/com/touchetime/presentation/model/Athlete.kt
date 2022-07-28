package com.touchetime.presentation.model

import com.touchetime.utils.state.ColorState

data class Athlete(
    val color: ColorState = ColorState.DEFAULT,
    var score: Int = 0,
    var foul: Int = 0,
    var touche: Boolean = false
)
