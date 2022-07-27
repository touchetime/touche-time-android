package com.touchetime.analytics

import android.content.Context
import android.util.Log
import com.touchetime.BuildConfig

class LogEventClient : AnalyticsClient {

    override fun init(context: Context) {}

    override fun identify(uuid: String) {}

    override fun sendEvent(event: AnalyticsEvent) {
        if (BuildConfig.DEBUG) {
            Log.d("LogEventClient", "EVENT => name: ${event.name} params: ${event.params}")
        }
    }

    override fun reset() {}
}
