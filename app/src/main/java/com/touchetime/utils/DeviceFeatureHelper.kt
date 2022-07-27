package com.touchetime.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

class DeviceFeatureHelper(context: Context) {

    var hasLowLatencyFeature: Boolean = false
        private set
    var hasProFeature: Boolean = false
        private set

    init {
        context.packageManager?.let { packageManager ->
            hasLowLatencyFeature =
                packageManager.hasSystemFeature(PackageManager.FEATURE_AUDIO_LOW_LATENCY)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hasProFeature = packageManager.hasSystemFeature(PackageManager.FEATURE_AUDIO_PRO)
            }
        }
    }
}
