package com.touchetime.data.model

import android.content.res.Resources
import com.touchetime.extensions.isScreenWithSmallWidthTablet

object Device {

    val os = OS.Android
    val category = if (Resources.getSystem().isScreenWithSmallWidthTablet) {
        Category.Tablet
    } else {
        Category.Smartphone
    }

    enum class Category(val value: String) {
        Smartphone("smartphone"),
        Tablet("tablet")
    }

    enum class OS(val value: String) {
        Android("android")
    }
}
