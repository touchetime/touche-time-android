package com.touchetime.extensions

import android.content.res.Resources

private const val SMALL_HEIGHT_DP = 700f
private const val SMALL_WIDTH_TABLET_DP = 600f

val Resources.isScreenWithSmallHeight: Boolean
    get() = (displayMetrics.heightPixels / displayMetrics.density) < SMALL_HEIGHT_DP

val Resources.isScreenWithSmallWidthTablet: Boolean
    get() = (displayMetrics.widthPixels / displayMetrics.density) >= SMALL_WIDTH_TABLET_DP
