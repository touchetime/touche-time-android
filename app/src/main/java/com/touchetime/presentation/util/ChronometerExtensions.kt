package com.touchetime.presentation.util // ktlint-disable filename

fun getTimeChronometer(value: Long): String =
    when (value) {
        Constants.TIME_ROUND_TREE_MINUTES -> "03:00"
        Constants.TIME_ROUND_TWO_MINUTES -> "02:00"
        Constants.TIME_INTERVAL -> "00:30"
        else -> {
            ""
        }
    }
