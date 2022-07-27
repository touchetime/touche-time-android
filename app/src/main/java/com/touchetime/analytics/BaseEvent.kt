package com.touchetime.analytics

import android.os.Build
import android.os.Bundle
import androidx.core.os.bundleOf
import com.touchetime.data.model.Device
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

abstract class BaseEvent(override val name: String) : AnalyticsEvent() {
    final override val params: Bundle = setupInitialData()

    companion object {
        private fun setupInitialData() = bundleOf(
            AnalyticsConstants.Keys.TIMESTAMP to getDateAndHourFormatted(),
            AnalyticsConstants.Keys.LOCALE to getLocale(),
            AnalyticsConstants.Keys.DEVICE_CATEGORY to Device.category.value,
            AnalyticsConstants.Keys.DEVICE_OS to getDeviceOs()
        )

        private fun getLocale() = Locale.getDefault().toString()

        private fun getDeviceOs() = "${Device.os.value} v${Build.VERSION.RELEASE}"

        private fun getDateAndHourFormatted(): String =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT)
            } else {
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Date())
            }
    }
}
