package com.touchetime.analytics

import android.os.Bundle

abstract class AnalyticsEvent {
    abstract val name: String
    abstract val params: Bundle

    override fun toString(): String {
        return "name: $name  params: $params"
    }
}
