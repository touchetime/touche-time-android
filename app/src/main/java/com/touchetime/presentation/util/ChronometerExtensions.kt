package com.touchetime.presentation.util // ktlint-disable filename

import com.touchetime.Constants.TIME_INTERVAL
import com.touchetime.Constants.TIME_ROUND_TREE_MINUTES
import com.touchetime.Constants.TIME_ROUND_TWO_MINUTES

fun getTimeChronometer(value: Long): String =
    when (value) {
        TIME_ROUND_TREE_MINUTES -> "03:00"
        TIME_ROUND_TWO_MINUTES -> "02:00"
        TIME_INTERVAL -> "00:30"
        else -> {
            ""
        }
    }
