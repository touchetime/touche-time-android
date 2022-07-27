package com.touchetime.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

object DeviceIDHelper {
    var deviceID: String? = null

    @SuppressLint("HardwareIds")
    fun retrieveDeviceId(context: Context) {
        deviceID = Settings.Secure.getString(
            context.applicationContext.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }
}
