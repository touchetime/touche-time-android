package com.touchetime.presentation.ui.fragments.style

import androidx.lifecycle.ViewModel
import com.touchetime.presentation.model.ObjectToSelect

class StyleViewModel : ViewModel() {

    var styleSelected: Int? = null
        private set

    fun getListStyle(): List<ObjectToSelect> {
        val listStyle = Style.getListStyle()

        listStyle.forEach {
            it.isSelected = it.params == styleSelected
        }

        return listStyle
    }

    fun setupStyleSelected(styleSelected: Int?) {
        this.styleSelected = styleSelected
    }
}
