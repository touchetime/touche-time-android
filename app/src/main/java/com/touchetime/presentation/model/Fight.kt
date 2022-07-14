package com.touchetime.presentation.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fight(
    @StringRes var category: Int? = null,
    @StringRes var style: Int? = null,
    var weight: Int? = null
) : Parcelable
