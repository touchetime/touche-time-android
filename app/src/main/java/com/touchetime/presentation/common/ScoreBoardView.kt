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
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        setupActivateListener(false)
    }

    fun setupScore(score: Int) {
        viewBinding.score.text = score.toString()
    }

    fun setupFoul(foul: Int) {
        viewBinding.foul.text = foul.toString()
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
            if (it.isEnabled) {
                callback()
            }
        }
    }

    fun setupRemoveScore(callback: () -> Unit) {
        viewBinding.removeScore.setOnClickListener {
            if (it.isEnabled) {
                callback()
            }
        }
    }

    fun setupAddFoul(callback: () -> Unit) {
        viewBinding.foulContainer.setOnClickListener {
            if (it.isEnabled) {
                callback()
            }
        }
    }

    fun setupRemoveFoul(callback: () -> Unit) {
        viewBinding.foulContainer.setOnLongClickListener {
            if (it.isEnabled) {
                callback()
            }
            true
        }
    }

    fun updateScore(value: String) {
        viewBinding.score.text = value
    }

    fun updateFoul(value: String) {
        viewBinding.foul.text = value
    }

    fun setupActivateListener(value: Boolean) {
        viewBinding.addScore.isEnabled = value
        viewBinding.removeScore.isEnabled = value
        viewBinding.foulContainer.isEnabled = value
        viewBinding.touche.isEnabled = value
    }

    fun openAddMoreScore(callback: () -> Unit) {
        viewBinding.addScore.setOnLongClickListener {
            callback()
            true
        }
    }

    fun openRemoveMoreScore(callback: () -> Unit) {
        viewBinding.removeScore.setOnLongClickListener {
            callback()
            true
        }
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
