package com.touchetime.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.touchetime.databinding.MoreScoreDialogFullscreenBinding
import com.touchetime.presentation.model.Athlete
import com.touchetime.presentation.state.ColorState

class MoreScoreDialogFullscreen(
    private val athlete: Athlete,
    val twoScore: () -> Unit,
    val fourScore: () -> Unit,
    val fiveScore: () -> Unit,
) : DialogFragmentTransparentBackground() {

    private lateinit var viewBinding: MoreScoreDialogFullscreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = MoreScoreDialogFullscreenBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupVisibilityScore()
        setupRedListener()
        setupBlueListener()
    }

    private fun setupVisibilityScore() {
        if (athlete.color == ColorState.RED) {
            viewBinding.moreScoreContainerRed.isVisible = true
        } else {
            viewBinding.moreScoreContainerBlue.isVisible = true
        }
    }

    private fun setupRedListener() {
        viewBinding.apply {
            this.closeRed.setOnClickListener {
                dismiss()
            }
            this.twoScoreRed.setOnClickListener {
                twoScore()
                dismiss()
            }
            this.fourScoreRed.setOnClickListener {
                fourScore()
                dismiss()
            }
            this.fiveScoreRed.setOnClickListener {
                fiveScore()
                dismiss()
            }
        }
    }

    private fun setupBlueListener() {
        viewBinding.apply {
            this.closeBlue.setOnClickListener {
                dismiss()
            }
            this.twoScoreBlue.setOnClickListener {
                twoScore()
                dismiss()
            }
            this.fourScoreBlue.setOnClickListener {
                fourScore()
                dismiss()
            }
            this.fiveScoreBlue.setOnClickListener {
                fiveScore()
                dismiss()
            }
        }
    }
}
