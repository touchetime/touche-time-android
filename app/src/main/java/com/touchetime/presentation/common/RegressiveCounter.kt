package com.touchetime.presentation.common

import android.os.CountDownTimer
import com.touchetime.presentation.util.changeToTwoCase

class RegressiveCounter(
    time: Long,
    private val regressiveCounterCallback: RegressiveCounterCallback
) : CountDownTimer(time, DELAY) {

    override fun onTick(millisUntilFinished: Long) {
        val minutes = (millisUntilFinished / 1000 / 60).toString()
        val seconds = (millisUntilFinished / 1000 % 60).toString()

        regressiveCounterCallback.onTick(
            minutes.changeToTwoCase(),
            seconds.changeToTwoCase(),
            millisUntilFinished
        )
    }

    override fun onFinish() {
        regressiveCounterCallback.finish()
    }

    companion object {
        const val DELAY = 1000L
    }

    interface RegressiveCounterCallback {
        fun onTick(minutes: String, seconds: String, millisUntilFinished: Long)
        fun finish()
    }
}
