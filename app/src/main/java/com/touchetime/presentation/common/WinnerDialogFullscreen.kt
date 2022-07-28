package com.touchetime.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.touchetime.R
import com.touchetime.databinding.WinnerDialogFullscreenBinding
import com.touchetime.presentation.model.Athlete
import com.touchetime.utils.state.ColorState

class WinnerDialogFullscreen(
    private val athlete: Athlete,
    val closeFight: () -> Unit,
    val restartFight: () -> Unit
) : DialogFragmentTransparentBackground() {

    private lateinit var viewBinding: WinnerDialogFullscreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = WinnerDialogFullscreenBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupVisibilityWinner()
        setupListener()
    }

    private fun setupToolbar() {
        viewBinding.toolbar.setupParams(title = "", icon = R.drawable.ic_close)
    }

    private fun setupVisibilityWinner() {
        if (athlete.color == ColorState.RED) {
            viewBinding.red.apply {
                isVisible = true
                setupColor(athlete.color.value)
                setupScore(athlete.score)
                setupFoul(athlete.foul)
            }
        } else {
            viewBinding.blue.apply {
                isVisible = true
                setupColor(athlete.color.value)
                setupScore(athlete.score)
                setupFoul(athlete.foul)
            }
        }
    }

    private fun setupListener() {
        viewBinding.restartFight.setOnClickListener {
            restartFight()
            dismiss()
        }

        viewBinding.toolbar.setupBack {
            closeFight()
            dismiss()
        }
    }
}
