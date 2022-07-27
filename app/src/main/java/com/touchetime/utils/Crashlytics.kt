package com.touchetime.utils

import com.google.firebase.crashlytics.FirebaseCrashlytics

object Crashlytics {
    private val firebaseCrashlytics get() = FirebaseCrashlytics.getInstance()

    fun log(message: String?) {
        message?.takeIf { it.isNotBlank() }?.let(firebaseCrashlytics::log)
    }

    fun log(exception: Throwable?) {
        exception?.let(firebaseCrashlytics::recordException)
    }
}
