package com.touchetime.presentation.ui.fragments.style

import com.touchetime.presentation.model.StyleSelected
import com.touchetime.utils.state.StyleState

object Style {

    private val listStyle: List<StyleSelected> = listOf(
        StyleSelected(StyleState.GRECO_ROMAN),
        StyleSelected(StyleState.WOMAN_WRESTLING),
        StyleSelected(StyleState.FREE_STYLE)
    )

    fun getListStyle(): List<StyleSelected> = listStyle
}
