package com.touchetime.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.touchetime.databinding.ChronometerViewBinding

class ChronometerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding = ChronometerViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setupMinutes(minutes: String, seconds: String) {
        viewBinding.apply {
            this.tenMinutes.text = minutes.substring(0, 1)
            this.minutes.text = minutes.substring(1)
            this.tenSeconds.text = seconds.substring(0, 1)
            this.seconds.text = seconds.substring(1)
        }
    }

    fun setupPlayPause(callback: () -> Unit) {
        viewBinding.playOrPause.setOnClickListener {
            callback()
        }
    }

    fun isRunning(): Boolean = viewBinding.playOrPause.isSelected

    fun setupIsRunning(value: Boolean) {
        viewBinding.playOrPause.isSelected = value
    }

    fun setupRound(round: Int) {
        viewBinding.round.text = context.getString(round)
    }

    fun setupPlayPauseVisibility(value: Boolean) {
        viewBinding.playOrPause.isVisible = value
    }
}
