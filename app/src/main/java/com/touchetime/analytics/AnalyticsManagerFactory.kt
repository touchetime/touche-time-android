package com.touchetime.analytics

object AnalyticsManagerFactory {
    inline fun <reified T : AnalyticsClient> create(): T? = when (T::class.java) {
        FirebaseAnalyticsClient::class.java -> FirebaseAnalyticsClient()
        LogEventClient::class.java -> LogEventClient()
        else -> null
    } as? T
}
