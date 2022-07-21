package com.touchetime.presentation.ui.fragments.customizefight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.touchetime.R
import com.touchetime.databinding.FragmentCustomizeFightBinding
import com.touchetime.presentation.common.BottomSheetDialogTransparentBackgroundFragment
import com.touchetime.presentation.model.Fight

class CustomizeFightFragment : BottomSheetDialogTransparentBackgroundFragment() {

    private lateinit var viewBinding: FragmentCustomizeFightBinding
    private val viewModel: CustomizeFightViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCustomizeFightBinding.inflate(
            inflater,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        readArgs()
        setupListener()
        setupSuperiorityTechnical()
        setupRounds()
        setupTimeRound()
        setupTimeInterval()
    }

    private fun readArgs() {
        (arguments?.getParcelable<Fight>(ARGS))?.let {
            viewModel.setupFight(it)
        }
    }

    private fun setupListener() {
        viewBinding.finish.setOnClickListener {
            dismiss()
        }
    }

    private fun setupSuperiorityTechnical() {
        viewBinding.superiorityTechnical.apply {
            this.setupTitle(R.string.superiority_technical)
            this.removeValue { }
            this.addValue { }
            this.updateValue(viewModel.fight.superiorityTechnical.toString())
        }
    }

    private fun setupRounds() {
        viewBinding.rounds.apply {
            this.setupTitle(R.string.rounds)
            this.removeValue { }
            this.addValue { }
            this.updateValue(viewModel.fight.numberRounds.toString())
        }
    }

    private fun setupTimeRound() {
        viewBinding.timeRound.apply {
            this.setupTitle(R.string.rounds)
            this.removeValue { }
            this.addValue { }
            this.updateValue(viewModel.fight.numberRounds.toString())
        }
    }

    private fun setupTimeInterval() {
        viewBinding.timeInterval.apply {
            this.setupTitle(R.string.rounds)
            this.removeValue { }
            this.addValue { }
            this.updateValue(viewModel.fight.numberRounds.toString())
        }
    }

    companion object {
        private const val ARGS = "ARGS"

        private fun newInstance(fight: Fight) = CustomizeFightFragment().apply {
            arguments = bundleOf(
                ARGS to fight
            )
        }

        fun show(fight: Fight, fragmentManager: FragmentManager) =
            newInstance(fight).show(fragmentManager, CustomizeFightFragment::class.java.name)
    }
}
