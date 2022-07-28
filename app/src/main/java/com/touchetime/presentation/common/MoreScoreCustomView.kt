package com.touchetime.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.touchetime.R
import com.touchetime.databinding.MoreScoreCustomViewBinding
import com.touchetime.presentation.model.Athlete
import com.touchetime.utils.state.ColorState

class MoreScoreCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding = MoreScoreCustomViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setupBackgroundColor(athlete: Athlete) {
        val background = if (athlete.color == ColorState.RED) {
            R.drawable.background_score_red
        } else {
            R.drawable.background_score_blue
        }

        viewBinding.apply {
            this.close.background = ContextCompat.getDrawable(context, background)
            this.fiveScore.background = ContextCompat.getDrawable(context, background)
            this.fourScore.background = ContextCompat.getDrawable(context, background)
            this.twoScore.background = ContextCompat.getDrawable(context, background)
        }
    }

    fun setupClose(callback: () -> Unit) {
        viewBinding.close.setOnClickListener { callback() }
    }

    fun setupFiveScore(callback: () -> Unit) {
        viewBinding.fiveScore.setOnClickListener { callback() }
    }

    fun setupFourScore(callback: () -> Unit) {
        viewBinding.fourScore.setOnClickListener { callback() }
    }

    fun setupTwoScore(callback: () -> Unit) {
        viewBinding.twoScore.setOnClickListener { callback() }
    }
}
