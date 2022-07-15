package com.touchetime.presentation.model

import android.os.Parcelable
import androidx.annotation.StringRes
import com.touchetime.presentation.state.CategoryState
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fight(
    var nameFight: String? = null,
    var category: CategoryState? = null,
    @StringRes var style: Int? = null,
    var weight: Int? = null
) : Parcelable
