package com.touchetime.analytics

import android.content.Context

interface AnalyticsClient {
    fun init(context: Context)
    fun identify(uuid: String)
    fun sendEvent(event: AnalyticsEvent)
    fun reset()
}
