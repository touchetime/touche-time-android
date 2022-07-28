package com.touchetime.utils.state

import androidx.annotation.StringRes
import com.touchetime.R

enum class RoundState(@StringRes val state: Int) {
    INTERVAL(R.string.interval),
    ROUND(R.string.round),
}
