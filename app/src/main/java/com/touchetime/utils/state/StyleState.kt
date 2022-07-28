package com.touchetime.utils.state

import android.os.Parcelable
import androidx.annotation.StringRes
import com.touchetime.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class StyleState(@StringRes val value: Int) : Parcelable {
    GRECO_ROMAN(R.string.greco_roman),
    WOMAN_WRESTLING(R.string.woman_wrestling),
    FREE_STYLE(R.string.free_style),
    DEFAULT(R.string.style)
}
