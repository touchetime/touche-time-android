package com.touchetime.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.touchetime.databinding.MoreScoreDialogFullscreenBinding
import com.touchetime.presentation.model.Athlete
import com.touchetime.utils.state.ColorState
import com.touchetime.utils.state.ScoreTypeState

class MoreScoreDialogFullscreen(
    private val athlete: Athlete,
    private val scoreTypeState: ScoreTypeState,
    val twoScore: () -> Unit,
    val fourScore: () -> Unit,
    val fiveScore: () -> Unit
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

        setupMoreScoreVisibility()
        setupAddMoreScoreRed()
        setupRemoveMoreScoreRed()
        setupAddMoreScoreBlue()
        setupRemoveMoreScoreBlue()
    }

    private fun setupMoreScoreVisibility() {
        if (scoreTypeState == ScoreTypeState.ADD) {
            if (athlete.color == ColorState.RED) {
                viewBinding.addMoreScoreRed.apply {
                    setupBackgroundColor(athlete)
                    isVisible = true
                }
            } else {
                viewBinding.addMoreScoreBlue.apply {
                    setupBackgroundColor(athlete)
                    isVisible = true
                }
            }
        } else {
            if (athlete.color == ColorState.RED) {
                viewBinding.removeMoreScoreRed.apply {
                    setupBackgroundColor(athlete)
                    isVisible = true
                }
            } else {
                viewBinding.removeMoreScoreBlue.apply {
                    setupBackgroundColor(athlete)
                    isVisible = true
                }
            }
        }
    }

    private fun setupAddMoreScoreRed() {
        viewBinding.addMoreScoreRed.apply {
            this.setupClose {
                dismiss()
            }
            this.setupFiveScore {
                fiveScore()
                dismiss()
            }
            this.setupFourScore {
                fourScore()
                dismiss()
            }
            this.setupTwoScore {
                twoScore()
                dismiss()
            }
        }
    }

    private fun setupRemoveMoreScoreRed() {
        viewBinding.removeMoreScoreRed.apply {
            this.setupClose {
                dismiss()
            }
            this.setupFiveScore {
                fiveScore()
                dismiss()
            }
            this.setupFourScore {
                fourScore()
                dismiss()
            }
            this.setupTwoScore {
                twoScore()
                dismiss()
            }
        }
    }

    private fun setupAddMoreScoreBlue() {
        viewBinding.addMoreScoreBlue.apply {
            this.setupClose {
                dismiss()
            }
            this.setupFiveScore {
                fiveScore()
                dismiss()
            }
            this.setupFourScore {
                fourScore()
                dismiss()
            }
            this.setupTwoScore {
                twoScore()
                dismiss()
            }
        }
    }

    private fun setupRemoveMoreScoreBlue() {
        viewBinding.removeMoreScoreBlue.apply {
            this.setupClose {
                dismiss()
            }
            this.setupFiveScore {
                fiveScore()
                dismiss()
            }
            this.setupFourScore {
                fourScore()
                dismiss()
            }
            this.setupTwoScore {
                twoScore()
                dismiss()
            }
        }
    }
}
