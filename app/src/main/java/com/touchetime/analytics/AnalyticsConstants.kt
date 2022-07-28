package com.touchetime.analytics

object AnalyticsConstants {

    object Events {
        object Fight {
            const val FIGHT_SCREEN_OPENED = "fight_screen_opened"
            const val FIGHT_FINISHED = "fight_finished"
        }
    }

    object Keys {
        const val LOCALE = "locale"
        const val TIMESTAMP = "timestamp"
        const val DEVICE_CATEGORY = "device_category"
        const val DEVICE_OS = "device_os"
        const val FIGHT_TYPE = "fight_type"
        const val CATEGORY = "category"
        const val STYLE = "style"
        const val WEIGHT = "weight"
        const val NAME = "name"
        const val ID = "id"
    }

    object UserTraits {
        const val HAS_LOW_LATENCY_FEATURE = "hasLowLatencyFeature"
        const val HAS_PRO_FEATURE = "hasProFeature"
    }
}
