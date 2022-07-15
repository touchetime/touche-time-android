package com.touchetime.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.touchetime.R
import com.touchetime.databinding.ScoreboardViewBinding

class ScoreBoardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding = ScoreboardViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setupScore(score: Int) {
        viewBinding.score.text = score.toString()
    }

    fun setupColor(@StringRes color: Int) {
        viewBinding.apply {
            if (color == R.string.blue) {
                buildScoreBoard(
                    R.drawable.background_scoreboard_blue,
                    R.drawable.background_score_blue,
                    R.drawable.background_button_blue_touche
                )
            } else {
                buildScoreBoard(
                    R.drawable.background_scoreboard_red,
                    R.drawable.background_score_red,
                    R.drawable.background_button_red_touche
                )
            }
        }
    }

    fun setupTouche(callback: () -> Unit) {
        viewBinding.touche.setOnClickListener {
            callback()
        }
    }

    fun setupAddScore(callback: () -> Unit) {
        viewBinding.addScore.setOnClickListener {
            callback()
        }
    }

    fun setupRemoveScore(callback: () -> Unit) {
        viewBinding.removeScore.setOnClickListener {
            callback()
        }
    }

    fun setupAddFoul(callback: () -> Unit) {
        viewBinding.foulContainer.setOnClickListener {
            callback()
        }
    }

    fun setupRemoveFoul(callback: () -> Unit) {
        viewBinding.foulContainer.setOnLongClickListener {
            callback()
            true
        }
    }

    fun updateScore(value: String) {
        viewBinding.score.text = value
    }

    fun updateFoul(value: String) {
        viewBinding.foul.text = value
    }

    private fun buildScoreBoard(
        @DrawableRes background: Int,
        @DrawableRes backgroundScore: Int,
        @DrawableRes backgroundTouche: Int
    ) {
        viewBinding.apply {
            this.container.background =
                ContextCompat.getDrawable(context, background)
            this.addScore.background =
                ContextCompat.getDrawable(
                    context,
                    backgroundScore
                )
            this.removeScore.background =
                ContextCompat.getDrawable(
                    context,
                    backgroundScore
                )
            this.touche.background =
                ContextCompat.getDrawable(context, backgroundTouche)
        }
    }
}
