package com.touchetime.presentation.util // ktlint-disable filename

fun String.formatToTwoCase(): String {
    return if (this.length < 2) {
        "0$this"
    } else {
        this
    }
}
