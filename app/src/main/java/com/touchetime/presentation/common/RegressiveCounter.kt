package com.touchetime.presentation.common

import android.os.CountDownTimer
import com.touchetime.utils.Constants.DELAY

class RegressiveCounter(
    time: Long,
    private val regressiveCounterCallback: RegressiveCounterCallback
) : CountDownTimer(time, DELAY) {

    override fun onTick(millisUntilFinished: Long) {
        regressiveCounterCallback.onTick(millisUntilFinished)
    }

    override fun onFinish() {
        regressiveCounterCallback.finish()
    }

    interface RegressiveCounterCallback {
        fun onTick(millisUntilFinished: Long)
        fun finish()
    }
}
