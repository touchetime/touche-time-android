package com.touchetime.presentation.common

import android.os.CountDownTimer
import com.touchetime.Constants.DELAY
import com.touchetime.presentation.util.formatToTwoCase

class RegressiveCounter(
    time: Long,
    private val regressiveCounterCallback: RegressiveCounterCallback
) : CountDownTimer(time, DELAY) {

    override fun onTick(millisUntilFinished: Long) {
        val minutes = (millisUntilFinished / 1000 / 60).toString()
        val seconds = (millisUntilFinished / 1000 % 60).toString()

        regressiveCounterCallback.onTick(
            minutes.formatToTwoCase(),
            seconds.formatToTwoCase(),
            millisUntilFinished
        )
    }

    override fun onFinish() {
        regressiveCounterCallback.finish()
    }

    interface RegressiveCounterCallback {
        fun onTick(minutes: String, seconds: String, millisUntilFinished: Long)
        fun finish()
    }
}
