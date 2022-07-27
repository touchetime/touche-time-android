package com.touchetime.extensions // ktlint-disable filename

fun String.formatToTwoCase(): String {
    return if (this.length < 2) {
        "0$this"
    } else {
        this
    }
}

fun String.buildRoundString(value: Int): String {
    return "$this $value"
}
