package com.touchetime.presentation.util // ktlint-disable filename

fun formatLongToTimeString(value: Long): String {
    val minutes = (value / 1000 / 60).toString()
    val seconds = (value / 1000 % 60).toString()

    return "${minutes.formatToTwoCase()}:${seconds.formatToTwoCase()}"
}
