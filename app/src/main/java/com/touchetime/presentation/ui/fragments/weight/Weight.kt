package com.touchetime.presentation.ui.fragments.weight

import com.touchetime.presentation.model.ObjectToSelect

object Weight {

    // Senior, U23 and U20
    val defaultGrecoRoman: List<ObjectToSelect> = listOf(
        ObjectToSelect(55, false),
        ObjectToSelect(60, false),
        ObjectToSelect(63, false),
        ObjectToSelect(67, false),
        ObjectToSelect(72, false),
        ObjectToSelect(77, false),
        ObjectToSelect(82, false),
        ObjectToSelect(87, false),
        ObjectToSelect(97, false),
        ObjectToSelect(130, false),
    )

    val defaultWomanWrestling: List<ObjectToSelect> = listOf(
        ObjectToSelect(50, false),
        ObjectToSelect(53, false),
        ObjectToSelect(55, false),
        ObjectToSelect(57, false),
        ObjectToSelect(59, false),
        ObjectToSelect(62, false),
        ObjectToSelect(65, false),
        ObjectToSelect(68, false),
        ObjectToSelect(72, false),
        ObjectToSelect(76, false),
    )

    val defaultFreeStyle: List<ObjectToSelect> = listOf(
        ObjectToSelect(57, false),
        ObjectToSelect(61, false),
        ObjectToSelect(65, false),
        ObjectToSelect(70, false),
        ObjectToSelect(74, false),
        ObjectToSelect(79, false),
        ObjectToSelect(86, false),
        ObjectToSelect(92, false),
        ObjectToSelect(97, false),
        ObjectToSelect(125, false),
    )

    // U17
    val u17GrecoRomanAndFreeStyle: List<ObjectToSelect> = listOf(
        ObjectToSelect(41, false),
        ObjectToSelect(45, false),
        ObjectToSelect(48, false),
        ObjectToSelect(51, false),
        ObjectToSelect(55, false),
        ObjectToSelect(60, false),
        ObjectToSelect(65, false),
        ObjectToSelect(71, false),
        ObjectToSelect(80, false),
        ObjectToSelect(92, false),
        ObjectToSelect(110, false),
    )

    val u17WomanWrestling: List<ObjectToSelect> = listOf(
        ObjectToSelect(40, false),
        ObjectToSelect(43, false),
        ObjectToSelect(46, false),
        ObjectToSelect(49, false),
        ObjectToSelect(53, false),
        ObjectToSelect(57, false),
        ObjectToSelect(61, false),
        ObjectToSelect(65, false),
        ObjectToSelect(69, false),
        ObjectToSelect(73, false),
    )

    // U15
    val u15GrecoRomanAndFreeStyle: List<ObjectToSelect> = listOf(
        ObjectToSelect(38, false),
        ObjectToSelect(41, false),
        ObjectToSelect(44, false),
        ObjectToSelect(52, false),
        ObjectToSelect(57, false),
        ObjectToSelect(62, false),
        ObjectToSelect(68, false),
        ObjectToSelect(75, false),
        ObjectToSelect(85, false),
    )

    val u15WomanWrestling: List<ObjectToSelect> = listOf(
        ObjectToSelect(33, false),
        ObjectToSelect(36, false),
        ObjectToSelect(39, false),
        ObjectToSelect(42, false),
        ObjectToSelect(46, false),
        ObjectToSelect(50, false),
        ObjectToSelect(54, false),
        ObjectToSelect(58, false),
        ObjectToSelect(62, false),
        ObjectToSelect(66, false),
    )

    // Children 3
    val children3AllWeight: List<ObjectToSelect> = listOf(
        ObjectToSelect(28, false),
        ObjectToSelect(31, false),
        ObjectToSelect(34, false),
        ObjectToSelect(38, false),
        ObjectToSelect(42, false),
        ObjectToSelect(47, false),
        ObjectToSelect(52, false),
        ObjectToSelect(55, false),
        ObjectToSelect(0, false), // When the weight is 0, should get the last weight and add +
    )

    // Children 2
    val children2AllWeight: List<ObjectToSelect> = listOf(
        ObjectToSelect(28, false),
        ObjectToSelect(30, false),
        ObjectToSelect(33, false),
        ObjectToSelect(36, false),
        ObjectToSelect(40, false),
        ObjectToSelect(45, false),
        ObjectToSelect(50, false),
        ObjectToSelect(55, false),
        ObjectToSelect(0, false), // When the weight is 0, should get the last weight and add +
    )

    // Children 1
    val children1AllWeight: List<ObjectToSelect> = listOf(
        ObjectToSelect(23, false),
        ObjectToSelect(26, false),
        ObjectToSelect(29, false),
        ObjectToSelect(32, false),
        ObjectToSelect(36, false),
        ObjectToSelect(40, false),
        ObjectToSelect(45, false),
        ObjectToSelect(50, false),
        ObjectToSelect(0, false), // When the weight is 0, should get the last weight and add +
    )
}