package com.touchetime.extensions // ktlint-disable filename

import android.app.Activity
import androidx.core.view.WindowCompat

fun Activity.setupFullScreenSystemUiFlags() {
    WindowCompat.setDecorFitsSystemWindows(window, false)
}
