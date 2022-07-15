package com.touchetime.presentation.state

import androidx.annotation.StringRes
import com.touchetime.R

enum class ColorState(@StringRes val value: Int) {
    RED(R.string.red),
    BLUE(R.string.blue),
    DEFAULT(R.string.athlete)
}
