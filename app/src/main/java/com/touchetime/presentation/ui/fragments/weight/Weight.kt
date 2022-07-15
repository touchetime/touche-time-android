package com.touchetime.presentation.ui.fragments.weight

import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.model.WeightSelect
import com.touchetime.presentation.state.CategoryState
import com.touchetime.presentation.state.StyleState

object Weight {

    fun getListWeight(
        fight: Fight,
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
        WeightSelect(55, false),
        WeightSelect(60, false),
        WeightSelect(63, false),
        WeightSelect(67, false),
        WeightSelect(72, false),
        WeightSelect(77, false),
        WeightSelect(82, false),
        WeightSelect(87, false),
        WeightSelect(97, false),
        WeightSelect(130, false),
    )

    private val defaultWomanWrestling: List<WeightSelect> = listOf(
        WeightSelect(50, false),
        WeightSelect(53, false),
        WeightSelect(55, false),
        WeightSelect(57, false),
        WeightSelect(59, false),
        WeightSelect(62, false),
        WeightSelect(65, false),
        WeightSelect(68, false),
        WeightSelect(72, false),
        WeightSelect(76, false),
    )

    private val defaultFreeStyle: List<WeightSelect> = listOf(
        WeightSelect(57, false),
        WeightSelect(61, false),
        WeightSelect(65, false),
        WeightSelect(70, false),
        WeightSelect(74, false),
        WeightSelect(79, false),
        WeightSelect(86, false),
        WeightSelect(92, false),
        WeightSelect(97, false),
        WeightSelect(125, false),
    )

    // U17
    private val u17GrecoRomanAndFreeStyle: List<WeightSelect> = listOf(
        WeightSelect(41, false),
        WeightSelect(45, false),
        WeightSelect(48, false),
        WeightSelect(51, false),
        WeightSelect(55, false),
        WeightSelect(60, false),
        WeightSelect(65, false),
        WeightSelect(71, false),
        WeightSelect(80, false),
        WeightSelect(92, false),
        WeightSelect(110, false),
    )

    private val u17WomanWrestling: List<WeightSelect> = listOf(
        WeightSelect(40, false),
        WeightSelect(43, false),
        WeightSelect(46, false),
        WeightSelect(49, false),
        WeightSelect(53, false),
        WeightSelect(57, false),
        WeightSelect(61, false),
        WeightSelect(65, false),
        WeightSelect(69, false),
        WeightSelect(73, false),
    )

    // U15
    private val u15GrecoRomanAndFreeStyle: List<WeightSelect> = listOf(
        WeightSelect(38, false),
        WeightSelect(41, false),
        WeightSelect(44, false),
        WeightSelect(52, false),
        WeightSelect(57, false),
        WeightSelect(62, false),
        WeightSelect(68, false),
        WeightSelect(75, false),
        WeightSelect(85, false),
    )

    private val u15WomanWrestling: List<WeightSelect> = listOf(
        WeightSelect(33, false),
        WeightSelect(36, false),
        WeightSelect(39, false),
        WeightSelect(42, false),
        WeightSelect(46, false),
        WeightSelect(50, false),
        WeightSelect(54, false),
        WeightSelect(58, false),
        WeightSelect(62, false),
        WeightSelect(66, false),
    )

    // Children 3
    private val children3AllWeight: List<WeightSelect> = listOf(
        WeightSelect(28, false),
        WeightSelect(31, false),
        WeightSelect(34, false),
        WeightSelect(38, false),
        WeightSelect(42, false),
        WeightSelect(47, false),
        WeightSelect(52, false),
        WeightSelect(55, false),
        WeightSelect(0, false), // When the weight is 0, should get the last weight and add +
    )

    // Children 2
    private val children2AllWeight: List<WeightSelect> = listOf(
        WeightSelect(28, false),
        WeightSelect(30, false),
        WeightSelect(33, false),
        WeightSelect(36, false),
        WeightSelect(40, false),
        WeightSelect(45, false),
        WeightSelect(50, false),
        WeightSelect(55, false),
        WeightSelect(0, false), // When the weight is 0, should get the last weight and add +
    )

    // Children 1
    private val children1AllWeight: List<WeightSelect> = listOf(
        WeightSelect(23, false),
        WeightSelect(26, false),
        WeightSelect(29, false),
        WeightSelect(32, false),
        WeightSelect(36, false),
        WeightSelect(40, false),
        WeightSelect(45, false),
        WeightSelect(50, false),
        WeightSelect(0, false), // When the weight is 0, should get the last weight and add +
    )
}
