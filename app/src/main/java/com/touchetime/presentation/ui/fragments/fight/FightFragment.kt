package com.touchetime.presentation.ui.fragments.fight

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.touchetime.R
import com.touchetime.databinding.FragmentFightBinding
import com.touchetime.presentation.common.BaseFragment
import com.touchetime.presentation.common.ChronometerView
import com.touchetime.presentation.common.RegressiveCounter
import com.touchetime.presentation.model.Athlete
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.state.AthleteState
import com.touchetime.presentation.state.RoundState
import com.touchetime.presentation.state.ScoreState
import com.touchetime.presentation.state.ScoreTypeState
import com.touchetime.presentation.ui.activity.main.MainActivity
import com.touchetime.presentation.ui.fragments.customizefight.CustomizeFightFragment
import com.touchetime.presentation.ui.fragments.home.HomeFragment
import com.touchetime.presentation.util.buildRoundString
import com.touchetime.presentation.util.formatLongToTimeString
import com.touchetime.presentation.util.showMoreScoreDialogFullscreen
import com.touchetime.presentation.util.showWinnerFullscreenDialog

class FightFragment : BaseFragment(), RegressiveCounter.RegressiveCounterCallback {

    private lateinit var viewBinding: FragmentFightBinding
    private val viewModel: FightViewModel by viewModels()
    private val mainActivity: MainActivity?
        get() = activity as? MainActivity
    private val resultKeys = arrayOf(
        CustomizeFightFragment.FIGHT_UPDATED
    )
    private lateinit var regressiveCounter: CountDownTimer
    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            navigateToHome()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFightBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(backPressedCallback)

        readArgs()
        setupToolbar()
        setupResultKeysListeners()
        setupRedObserver()
        setupBlueObserver()
        setupFightObserver()
        setupTimeObserver()
        setupRoundObserver()
        setupScoreboard()
        setupRegressiveChronometerListener()
        setupListeners()
    }

    override fun onDestroyView() {
        backPressedCallback.remove()
        super.onDestroyView()
    }

    private fun readArgs() {
        (arguments?.getParcelable<Fight>(ARGS))?.let {
            checkCustomFightVisibility(it)
            viewModel.setupFight(it)
        }
    }

    private fun setupToolbar() {
        viewBinding.toolbar.apply {
            this.setupParams(title = getString(R.string.card_custom_view_title_1))
            this.setupBack { navigateToHome() }
        }
    }

    private fun setupResultKeysListeners() {
        resultKeys.forEach {
            when (it) {
                CustomizeFightFragment.FIGHT_UPDATED
                -> childFragmentManager
                else -> activity?.supportFragmentManager
            }?.setFragmentResultListener(
                it,
                viewLifecycleOwner,
                ::handleResultKey
            )
        }
    }

    private fun handleResultKey(key: String, bundle: Bundle) {
        when (key) {
            CustomizeFightFragment.FIGHT_UPDATED -> updateFight(bundle)
        }
    }

    private fun updateFight(bundle: Bundle) {
        (bundle.getParcelable<Fight>(CustomizeFightFragment.FIGHT))?.let {
            viewModel.updateFight(it)
        }
    }

    private fun setupRedObserver() {
        viewModel.athleteRed.observe(viewLifecycleOwner) {
            when (it) {
                is AthleteState.AthleteDefault -> {
                    updateScoreRed(it.athlete.score.toString())
                    updateFoulRed(it.athlete.foul.toString())
                }
                is AthleteState.AthleteAddScore -> {
                    updateScoreRed(it.score.toString())
                }
                is AthleteState.AthleteRemoveScore -> {
                    updateScoreRed(it.score.toString())
                }
                is AthleteState.AthleteAddFoul -> {
                    updateFoulRed(it.foul.toString())
                }
                is AthleteState.AthleteRemoveFoul -> {
                    updateFoulRed(it.foul.toString())
                }
                is AthleteState.AthleteAddTouche, is AthleteState.AthleteWin -> {
                    setupScoreClickable(false)
                    pauseRegressiveCounter()

                    showWinnerFullscreenDialog(
                        viewModel.athleteRedUpdated,
                        ::fightEnded,
                        ::restartFight
                    )
                }
            }
        }
    }

    private fun setupBlueObserver() {
        viewModel.athleteBlue.observe(viewLifecycleOwner) {
            when (it) {
                is AthleteState.AthleteDefault -> {
                    updateScoreBlue(it.athlete.score.toString())
                    updateFoulBlue(it.athlete.foul.toString())
                }
                is AthleteState.AthleteAddScore -> {
                    updateScoreBlue(it.score.toString())
                }
                is AthleteState.AthleteRemoveScore -> {
                    updateScoreBlue(it.score.toString())
                }
                is AthleteState.AthleteAddFoul -> {
                    updateFoulBlue(it.foul.toString())
                }
                is AthleteState.AthleteRemoveFoul -> {
                    updateFoulBlue(it.foul.toString())
                }
                is AthleteState.AthleteAddTouche, is AthleteState.AthleteWin -> {
                    setupScoreClickable(false)
                    pauseRegressiveCounter()

                    showWinnerFullscreenDialog(
                        viewModel.athleteBlueUpdated,
                        ::fightEnded,
                        ::restartFight
                    )
                }
            }
        }
    }

    private fun setupFightObserver() {
        viewModel.fight.observe(viewLifecycleOwner) {
            viewModel.setupChronometerToRound()
            viewBinding.toolbar.setupParams(title = it.nameFight)
        }
    }

    private fun setupTimeObserver() {
        viewModel.time.observe(viewLifecycleOwner) {
            setupTime(it)
        }
    }

    private fun setupRoundObserver() {
        viewModel.round.observe(viewLifecycleOwner) {
            setupRound(it)
        }
    }

    private fun setupScoreboard() {
        viewBinding.apply {
            this.red.setupColor(R.string.red)
            this.blue.setupColor(R.string.blue)
        }
    }

    private fun setupRegressiveChronometerListener() {
        viewBinding.regressiveCounter.apply {
            this.setupPlayPause { regressiveChronometerPlayPause() }
            this.setupReset { restartFight() }
            this.setupEdit {
                viewModel.fight.value?.let {
                    CustomizeFightFragment.show(
                        it,
                        childFragmentManager
                    )
                }
            }
        }
    }

    private fun setupListeners() {
        setupRed()
        setupBlue()
    }

    private fun setupRed() {
        viewBinding.red.apply {
            this.setupAddScore { setupAddScoreRed(ScoreState.ONE) }
            this.openAddMoreScore { openAddMoreScoreRed() }
            this.setupRemoveScore { setupRemoveScoreRed(ScoreState.ONE) }
            this.openRemoveMoreScore { openRemoveMoreScoreRed() }
            this.setupTouche { viewModel.setupToucheRed() }
            this.setupAddFoul { viewModel.setupAddFoulRed() }
            this.setupRemoveFoul { viewModel.setupRemoveFoulRed() }
        }
    }

    private fun setupBlue() {
        viewBinding.blue.apply {
            this.setupAddScore { setupAddScoreBlue(ScoreState.ONE) }
            this.openAddMoreScore { openAddMoreScoreBlue() }
            this.setupRemoveScore { setupRemoveScoreBlue(ScoreState.ONE) }
            this.openRemoveMoreScore { openRemoveMoreScoreBlue() }
            this.setupTouche { viewModel.setupToucheBlue() }
            this.setupAddFoul { viewModel.setupAddFoulBlue() }
            this.setupRemoveFoul { viewModel.setupRemoveFoulBlue() }
        }
    }

    private fun fightEnded() {
        mainActivity?.navigateToFragment(
            HomeFragment.newInstance(),
            HomeFragment::class.java.name
        )
    }

    private fun restartFight() {
        viewModel.resetFight()
        setupRegressiveCounterIsRunning(false)
    }

    private fun openAddMoreScoreRed() {
        showMoreScore(
            viewModel.athleteRedUpdated,
            ScoreTypeState.ADD,
            { setupAddScoreRed(ScoreState.TWO) },
            { setupAddScoreRed(ScoreState.FOUR) },
            { setupAddScoreRed(ScoreState.FIVE) }
        )
    }

    private fun openRemoveMoreScoreRed() {
        showMoreScore(
            viewModel.athleteRedUpdated,
            ScoreTypeState.REMOVE,
            { setupRemoveScoreRed(ScoreState.TWO) },
            { setupRemoveScoreRed(ScoreState.FOUR) },
            { setupRemoveScoreRed(ScoreState.FIVE) }
        )
    }

    private fun openAddMoreScoreBlue() {
        showMoreScore(
            viewModel.athleteBlueUpdated,
            ScoreTypeState.ADD,
            { setupAddScoreBlue(ScoreState.TWO) },
            { setupAddScoreBlue(ScoreState.FOUR) },
            { setupAddScoreBlue(ScoreState.FIVE) }
        )
    }

    private fun openRemoveMoreScoreBlue() {
        showMoreScore(
            viewModel.athleteBlueUpdated,
            ScoreTypeState.REMOVE,
            { setupRemoveScoreBlue(ScoreState.TWO) },
            { setupRemoveScoreBlue(ScoreState.FOUR) },
            { setupRemoveScoreBlue(ScoreState.FIVE) }
        )
    }

    private fun setupAddScoreRed(scoreState: ScoreState) {
        viewModel.setupAddScoreRed(scoreState)
    }

    private fun setupAddScoreBlue(scoreState: ScoreState) {
        viewModel.setupAddScoreBlue(scoreState)
    }

    private fun setupRemoveScoreRed(scoreState: ScoreState) {
        viewModel.setupRemoveScoreRed(scoreState)
    }

    private fun setupRemoveScoreBlue(scoreState: ScoreState) {
        viewModel.setupRemoveScoreBlue(scoreState)
    }

    private fun showMoreScore(
        athlete: Athlete,
        scoreTypeState: ScoreTypeState,
        twoScore: () -> Unit,
        fourScore: () -> Unit,
        fiveScore: () -> Unit
    ) {
        showMoreScoreDialogFullscreen(
            athlete,
            scoreTypeState,
            twoScore,
            fourScore,
            fiveScore
        )
    }

    private fun setupScoreClickable(value: Boolean) {
        viewBinding.apply {
            red.setupActivateListener(value)
            blue.setupActivateListener(value)
        }
    }

    private fun setupTime(time: Long) {
        viewBinding.regressiveCounter.setupMinutes(formatLongToTimeString(time))
    }

    private fun startRegressiveCounter(timerRound: Long) {
        setupScoreClickable(true)

        regressiveCounter = RegressiveCounter(
            time = timerRound,
            regressiveCounterCallback = this
        ).start()
    }

    private fun pauseRegressiveCounter() {
        regressiveCounter.cancel()
    }

    private fun setupRound(roundState: RoundState) {
        val text = if (roundState == RoundState.ROUND) {
            getString(roundState.state)
                .buildRoundString(viewModel.currentRound)
        } else {
            getString(roundState.state)
        }

        viewBinding.regressiveCounter.setupRound(text)
    }

    private fun setupRegressiveCounterIsRunning(value: Boolean) {
        viewBinding.regressiveCounter.apply {
            setupIsRunning(value)
            setupCustomVisibility(!value)
        }
    }

    private fun updateScoreRed(value: String) {
        viewBinding.red.updateScore(value)
    }

    private fun updateFoulRed(value: String) {
        viewBinding.red.updateFoul(value)
    }

    private fun updateScoreBlue(value: String) {
        viewBinding.blue.updateScore(value)
    }

    private fun updateFoulBlue(value: String) {
        viewBinding.blue.updateFoul(value)
    }

    private fun setupChronometerRound() {
        viewModel.apply {
            updateCurrentRound()
            setupRound(RoundState.ROUND)
            setupIsInRound(true)
            setupChronometerToRound()
        }
    }

    private fun setupChronometerInterval() {
        viewModel.apply {
            setupRound(RoundState.INTERVAL)
            setupIsInRound(false)
            fight.value?.timeInterval?.let {
                setupTimeChronometer(it)
                setupTimerRounder(it)
            }
        }
    }

    private fun navigateToHome() {
        mainActivity?.navigateToFragment(
            HomeFragment.newInstance(),
            HomeFragment::class.java.name
        )
    }

    private fun checkCustomFightVisibility(fight: Fight) {
        viewBinding.regressiveCounter.setupCustomVisibility(fight.isCustom)
    }

    private fun ChronometerView.regressiveChronometerPlayPause() {
        if (this.isRunning()) {
            setupRegressiveCounterIsRunning(false)
            pauseRegressiveCounter()
        } else {
            setupRegressiveCounterIsRunning(true)
            startRegressiveCounter(viewModel.timerRound)
        }
    }

    override fun onTick(millisUntilFinished: Long) {
        viewModel.setupTimerRounder(millisUntilFinished)
        setupTime(millisUntilFinished)
    }

    override fun finish() {
        viewModel.apply {
            if (currentRound == fight.value?.numberRounds) {
                finishFight()
            } else {
                if (roundIsFinished) {
                    setupChronometerInterval()
                } else {
                    setupChronometerRound()
                }
            }

            fight.value?.let { checkCustomFightVisibility(it) }
        }

        viewBinding.regressiveCounter.apply {
            setupIsRunning(false)
            setupPlayPauseVisibility(true)
        }
    }

    companion object {
        private const val ARGS = "ARGS"

        private fun newInstance(fight: Fight) = FightFragment().apply {
            arguments = bundleOf(
                ARGS to fight
            )
        }

        fun show(fight: Fight) = newInstance(fight)
    }
}
