package com.touchetime.presentation.ui.fragments.weight

import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.model.WeightSelect
import com.touchetime.presentation.state.CategoryState
import com.touchetime.presentation.state.StyleState

object Weight {

    fun getListWeight(
        fight: Fight
    ): List<WeightSelect> {
        return when (fight.category) {
            CategoryState.CHILDREN_1 -> {
                children1AllWeight
            }
            CategoryState.CHILDREN_2 -> {
                children2AllWeight
            }
            CategoryState.CHILDREN_3 -> {
                children3AllWeight
            }
            CategoryState.U15 -> {
                getListWeightU15ByStyle(fight.style)
            }
            CategoryState.U17 -> {
                getListWeightU17ByStyle(fight.style)
            }
            else -> {
                getListWeightDefaultByStyle(fight.style) // This situation will no occur
            }
        }
    }

    private fun getListWeightU15ByStyle(styleState: StyleState): List<WeightSelect> {
        return when (styleState) {
            StyleState.GRECO_ROMAN, StyleState.FREE_STYLE -> {
                u15GrecoRomanAndFreeStyle
            }
            StyleState.WOMAN_WRESTLING -> {
                u15WomanWrestling
            }
            StyleState.DEFAULT -> {
                listOf()
            }
        }
    }

    private fun getListWeightU17ByStyle(styleState: StyleState): List<WeightSelect> {
        return when (styleState) {
            StyleState.GRECO_ROMAN, StyleState.FREE_STYLE -> {
                u17GrecoRomanAndFreeStyle
            }
            StyleState.WOMAN_WRESTLING -> {
                u17WomanWrestling
            }
            StyleState.DEFAULT -> {
                listOf()
            }
        }
    }

    private fun getListWeightDefaultByStyle(styleState: StyleState): List<WeightSelect> {
        return when (styleState) {
            StyleState.GRECO_ROMAN -> {
                defaultGrecoRoman
            }
            StyleState.WOMAN_WRESTLING -> {
                defaultWomanWrestling
            }
            StyleState.FREE_STYLE -> {
                defaultFreeStyle
            }
            StyleState.DEFAULT -> {
                listOf()
            }
        }
    }

    // Senior, U23 and U20
    private val defaultGrecoRoman: List<WeightSelect> = listOf(
        WeightSelect("-55Kg", false),
        WeightSelect("-60Kg", false),
        WeightSelect("-63Kg", false),
        WeightSelect("-67Kg", false),
        WeightSelect("-72Kg", false),
        WeightSelect("-77Kg", false),
        WeightSelect("-82Kg", false),
        WeightSelect("-87Kg", false),
        WeightSelect("-97Kg", false),
        WeightSelect("-130Kg", false)
    )

    private val defaultWomanWrestling: List<WeightSelect> = listOf(
        WeightSelect("-50Kg", false),
        WeightSelect("-53Kg", false),
        WeightSelect("-55Kg", false),
        WeightSelect("-57Kg", false),
        WeightSelect("-59Kg", false),
        WeightSelect("-62Kg", false),
        WeightSelect("-65Kg", false),
        WeightSelect("-68Kg", false),
        WeightSelect("-72Kg", false),
        WeightSelect("-76Kg", false)
    )

    private val defaultFreeStyle: List<WeightSelect> = listOf(
        WeightSelect("-57Kg", false),
        WeightSelect("-61Kg", false),
        WeightSelect("-65Kg", false),
        WeightSelect("-70Kg", false),
        WeightSelect("-74Kg", false),
        WeightSelect("-79Kg", false),
        WeightSelect("-86Kg", false),
        WeightSelect("-82Kg", false),
        WeightSelect("-97Kg", false),
        WeightSelect("-125Kg", false)
    )

    // U17
    private val u17GrecoRomanAndFreeStyle: List<WeightSelect> = listOf(
        WeightSelect("-41Kg", false),
        WeightSelect("-45Kg", false),
        WeightSelect("-48Kg", false),
        WeightSelect("-51Kg", false),
        WeightSelect("-55Kg", false),
        WeightSelect("-60Kg", false),
        WeightSelect("-65Kg", false),
        WeightSelect("-71Kg", false),
        WeightSelect("-80Kg", false),
        WeightSelect("-92Kg", false),
        WeightSelect("-110Kg", false)
    )

    private val u17WomanWrestling: List<WeightSelect> = listOf(
        WeightSelect("-40Kg", false),
        WeightSelect("-43Kg", false),
        WeightSelect("-46Kg", false),
        WeightSelect("-49Kg", false),
        WeightSelect("-53Kg", false),
        WeightSelect("-57Kg", false),
        WeightSelect("-61Kg", false),
        WeightSelect("-65Kg", false),
        WeightSelect("-96Kg", false),
        WeightSelect("-73Kg", false)
    )

    // U15
    private val u15GrecoRomanAndFreeStyle: List<WeightSelect> = listOf(
        WeightSelect("-38Kg", false),
        WeightSelect("-41Kg", false),
        WeightSelect("-44Kg", false),
        WeightSelect("-52Kg", false),
        WeightSelect("-57Kg", false),
        WeightSelect("-62Kg", false),
        WeightSelect("-68Kg", false),
        WeightSelect("-75Kg", false),
        WeightSelect("-85Kg", false)
    )

    private val u15WomanWrestling: List<WeightSelect> = listOf(
        WeightSelect("-33Kg", false),
        WeightSelect("-36Kg", false),
        WeightSelect("-39Kg", false),
        WeightSelect("-42Kg", false),
        WeightSelect("-46Kg", false),
        WeightSelect("-50Kg", false),
        WeightSelect("-54Kg", false),
        WeightSelect("-58Kg", false),
        WeightSelect("-62Kg", false),
        WeightSelect("-66Kg", false)
    )

    // Children 3
    private val children3AllWeight: List<WeightSelect> = listOf(
        WeightSelect("-28Kg", false),
        WeightSelect("-31Kg", false),
        WeightSelect("-34Kg", false),
        WeightSelect("-38Kg", false),
        WeightSelect("-42Kg", false),
        WeightSelect("-47Kg", false),
        WeightSelect("-52Kg", false),
        WeightSelect("-55Kg", false),
        WeightSelect("+55Kg", false)
    )

    // Children 2
    private val children2AllWeight: List<WeightSelect> = listOf(
        WeightSelect("-28Kg", false),
        WeightSelect("-30Kg", false),
        WeightSelect("-33Kg", false),
        WeightSelect("-36Kg", false),
        WeightSelect("-40Kg", false),
        WeightSelect("-45Kg", false),
        WeightSelect("-50Kg", false),
        WeightSelect("-55Kg", false),
        WeightSelect("+55Kg", false)
    )

    // Children 1
    private val children1AllWeight: List<WeightSelect> = listOf(
        WeightSelect("-23Kg", false),
        WeightSelect("-36Kg", false),
        WeightSelect("-39Kg", false),
        WeightSelect("-32Kg", false),
        WeightSelect("-36Kg", false),
        WeightSelect("-40Kg", false),
        WeightSelect("-45Kg", false),
        WeightSelect("-50Kg", false),
        WeightSelect("+50Kg", false)
    )
}
