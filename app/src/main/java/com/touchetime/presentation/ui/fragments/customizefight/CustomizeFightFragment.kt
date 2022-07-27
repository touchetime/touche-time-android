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
import com.touchetime.extensions.formatLongToTimeString
import com.touchetime.presentation.common.BottomSheetDialogTransparentBackgroundFragment
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.state.TimeState

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
        setupFightObserver()
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
            parentFragmentManager.setFragmentResult(
                FIGHT_UPDATED,
                bundleOf(
                    FIGHT to viewModel.fight.value
                )
            )
            dismiss()
        }
    }

    private fun setupFightObserver() {
        viewModel.fight.observe(viewLifecycleOwner) {
            showValueUpdated(it)
        }
    }

    private fun setupSuperiorityTechnical() {
        viewBinding.superiorityTechnical.apply {
            this.setupTitle(R.string.superiority_technical)
            this.removeValue { viewModel.removeSuperiorityTechnical() }
            this.addValue { viewModel.addSuperiorityTechnical() }
        }
    }

    private fun setupRounds() {
        viewBinding.rounds.apply {
            this.setupTitle(R.string.rounds)
            this.removeValue { viewModel.removeRounds() }
            this.addValue { viewModel.addRounds() }
        }
    }

    private fun setupTimeRound() {
        viewBinding.timeRound.apply {
            this.setupTitle(R.string.time_rounds)
            this.removeValue { viewModel.removeTimeRound(TimeState.TEN_SECONDS) }
            this.removeLongValue { viewModel.removeTimeRound(TimeState.ONE_MINUTES) }
            this.addValue { viewModel.addTimeRound(TimeState.TEN_SECONDS) }
            this.addLongValue { viewModel.addTimeRound(TimeState.ONE_MINUTES) }
        }
    }

    private fun setupTimeInterval() {
        viewBinding.timeInterval.apply {
            this.setupTitle(R.string.time_intervals)
            this.removeValue { viewModel.removeTimeInterval(TimeState.TEN_SECONDS) }
            this.removeLongValue { viewModel.removeTimeInterval(TimeState.ONE_MINUTES) }
            this.addValue { viewModel.addTimeInterval(TimeState.TEN_SECONDS) }
            this.addLongValue { viewModel.addTimeInterval(TimeState.ONE_MINUTES) }
        }
    }

    private fun showValueUpdated(fight: Fight) {
        viewBinding.apply {
            superiorityTechnical.updateValue(fight.superiorityTechnical.toString())
            rounds.updateValue(fight.numberRounds.toString())
            timeRound.updateValue(formatLongToTimeString(fight.timeRound))
            timeInterval.updateValue(formatLongToTimeString(fight.timeInterval))
        }
    }

    companion object {
        private const val ARGS = "ARGS"
        const val FIGHT = "FIGHT"
        const val FIGHT_UPDATED = "FIGHT_UPDATED"

        private fun newInstance(fight: Fight) = CustomizeFightFragment().apply {
            arguments = bundleOf(
                ARGS to fight
            )
        }

        fun show(fight: Fight, fragmentManager: FragmentManager) =
            newInstance(fight).show(fragmentManager, CustomizeFightFragment::class.java.name)
    }
}
