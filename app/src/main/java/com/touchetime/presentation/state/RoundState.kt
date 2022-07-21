package com.touchetime.presentation.state

import androidx.annotation.StringRes
import com.touchetime.R

enum class RoundState(@StringRes val value: Int) {
    ROUND_ONE(R.string.round_one),
    INTERVAL(R.string.interval),
    ROUND_TWO(R.string.round_two),
}
