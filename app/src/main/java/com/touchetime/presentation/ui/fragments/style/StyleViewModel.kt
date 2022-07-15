package com.touchetime.presentation.ui.fragments.style

import androidx.lifecycle.ViewModel
import com.touchetime.presentation.model.StyleSelected
import com.touchetime.presentation.state.StyleState

class StyleViewModel : ViewModel() {

    var styleSelected: StyleState = StyleState.DEFAULT
        private set

    fun getListStyle(): List<StyleSelected> {
        val listStyle = Style.getListStyle()

        listStyle.forEach {
            it.isSelected = it.enum == styleSelected
        }

        return listStyle
    }

    fun setupStyleSelected(styleSelected: StyleState) {
        this.styleSelected = styleSelected
    }
}
