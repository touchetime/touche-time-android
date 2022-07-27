package com.touchetime.analytics

import android.content.Context
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import com.touchetime.utils.Crashlytics
import com.touchetime.utils.DeviceFeatureHelper

class FirebaseAnalyticsClient : AnalyticsClient {
    override fun init(context: Context) {
        setupDefaultUserProperties(DeviceFeatureHelper(context.applicationContext))
    }

    override fun identify(uuid: String) {
        setUserId(uuid)
    }

    override fun sendEvent(event: AnalyticsEvent) {
        try {
            Firebase.analytics.logEvent(event.name, event.params)
        } catch (e: OutOfMemoryError) {
            Crashlytics.log(e)
        }
    }

    override fun reset() {
        setUserId(null)
    }

    private fun setUserId(userId: String?) {
        Firebase.analytics.setUserId(userId)
        FirebaseCrashlytics.getInstance().setUserId(userId ?: "")
    }

    private fun setupDefaultUserProperties(deviceFeatureHelper: DeviceFeatureHelper) {
        Firebase.analytics.apply {
            setUserProperty(
                AnalyticsConstants.UserTraits.HAS_PRO_FEATURE,
                deviceFeatureHelper.hasProFeature.toString()
            )
            setUserProperty(
                AnalyticsConstants.UserTraits.HAS_LOW_LATENCY_FEATURE,
                deviceFeatureHelper.hasLowLatencyFeature.toString()
            )
        }
    }
}
