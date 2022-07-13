package com.touchetime.presentation.ui.fragments.category

import com.touchetime.R
import com.touchetime.presentation.model.ObjectToSelect

object Category {

    private val listCategory: List<ObjectToSelect> = listOf(
        ObjectToSelect(R.string.u13),
        ObjectToSelect(R.string.u15),
        ObjectToSelect(R.string.u17),
        ObjectToSelect(R.string.u20),
        ObjectToSelect(R.string.senior),
    )

    fun getListCategory(): List<ObjectToSelect> = listCategory
}
