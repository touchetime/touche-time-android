package com.touchetime.analytics

import android.content.Context
import com.touchetime.BuildConfig

object AnalyticsManager : AnalyticsClient {
    private var clients: ArrayList<AnalyticsClient> = arrayListOf(FirebaseAnalyticsClient(), LogEventClient())

    override fun init(context: Context) {
        AnalyticsManagerFactory.create<FirebaseAnalyticsClient>()?.also {
            it.init(context.applicationContext)
            clients.add(it)
        }
        if (BuildConfig.DEBUG) {
            AnalyticsManagerFactory.create<LogEventClient>()?.also {
                clients.add(it)
            }
        }
    }

    override fun identify(uuid: String) {
        callForAllManagers {
            identify(uuid)
        }
    }

    override fun sendEvent(event: AnalyticsEvent) {
        callForAllManagers {
            sendEvent(event)
        }
    }

    override fun reset() {
        callForAllManagers {
            reset()
        }
    }

    private fun callForAllManagers(action: AnalyticsClient.() -> Unit) {
        clients.forEach(action)
    }
}
