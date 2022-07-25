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

    fun setupMinutes(time: String) {
        viewBinding.apply {
            this.tenMinutes.text = time.substring(0, 1)
            this.minutes.text = time.substring(1, 2)
            this.tenSeconds.text = time.substring(3, 4)
            this.seconds.text = time.substring(4)
        }
    }

    fun setupCustomVisibility(value: Boolean) {
        viewBinding.apply {
            reset.isVisible = value
            edit.isVisible = value
        }
    }

    fun setupPlayPause(callback: () -> Unit) {
        viewBinding.playOrPause.setOnClickListener {
            callback()
        }
    }

    fun setupReset(callback: () -> Unit) {
        viewBinding.reset.setOnClickListener {
            callback()
        }
    }

    fun setupEdit(callback: () -> Unit) {
        viewBinding.edit.setOnClickListener {
            callback()
        }
    }

    fun isRunning(): Boolean = viewBinding.playOrPause.isSelected

    fun setupIsRunning(value: Boolean) {
        viewBinding.playOrPause.isSelected = value
    }

    fun setupRound(round: String) {
        viewBinding.round.text = round
    }

    fun setupPlayPauseVisibility(value: Boolean) {
        viewBinding.playOrPause.isVisible = value
    }
}
