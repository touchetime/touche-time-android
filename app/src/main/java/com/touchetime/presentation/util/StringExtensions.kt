package com.touchetime.presentation.util

fun String.changeToTwoCase(): String {
    return if (this.length < 2) {
        "0$this"
    } else {
        this
    }
}
