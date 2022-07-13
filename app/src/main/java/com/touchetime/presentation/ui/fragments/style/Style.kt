package com.touchetime.presentation.ui.fragments.style

import com.touchetime.R
import com.touchetime.presentation.model.ObjectToSelect

object Style {

    private val listStyle: List<ObjectToSelect> = listOf(
        ObjectToSelect(R.string.greco_roman),
        ObjectToSelect(R.string.woman_wrestling),
        ObjectToSelect(R.string.free_style),
    )

    fun getListStyle(): List<ObjectToSelect> = listStyle
}
