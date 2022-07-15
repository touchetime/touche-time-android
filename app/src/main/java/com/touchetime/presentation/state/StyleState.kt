package com.touchetime.presentation.state

import androidx.annotation.StringRes
import com.touchetime.R

enum class StyleState(@StringRes val value: Int) {
    GRECO_ROMAN(R.string.greco_roman),
    WOMAN_WRESTLING(R.string.woman_wrestling),
    FREE_STYLE(R.string.free_style)
}
