package com.touchetime.presentation.ui.fragments.fight

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.touchetime.R
import com.touchetime.databinding.FragmentFightBinding
import com.touchetime.presentation.common.RegressiveCounter
import com.touchetime.presentation.model.Athlete
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.state.AthleteState
import com.touchetime.presentation.state.ScoreState
import com.touchetime.presentation.state.ScoreTypeState
import com.touchetime.presentation.ui.activity.main.MainActivity
import com.touchetime.presentation.ui.fragments.home.HomeFragment
import com.touchetime.presentation.util.showMoreScoreDialogFullscreen
import com.touchetime.presentation.util.showWinnerFullscreenDialog

class FightFragment : Fragment(), RegressiveCounter.RegressiveCounterCallback {

    private lateinit var viewBinding: FragmentFightBinding
    private val mainActivity: MainActivity?
        get() = activity as? MainActivity
    private lateinit var regressiveCounter: CountDownTimer
    private val viewModel: FightViewModel by viewModels()

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

        readArgs()
        setupTimeRound(TIME_ROUND)
        setupToolbar()
        setupObservers()
        setupScoreboard()
        setupRed()
        setupBlue()
        setupChronometer(
            R.string.tree_minutes,
            R.string.zero_minutes
        )
        setupPlayPause()
    }

    private fun readArgs() {
        (arguments?.getParcelable<Fight>(ARGS))?.let {
            viewModel.setupFight(it)
        }
    }

    private fun setupObservers() {
        viewModel.athleteRed.observe(viewLifecycleOwner) {
            when (it) {
                is AthleteState.AthleteDefault -> {
                    updateScoreRed(it.athlete.score.toString())
                    updateFoulRed(it.athlete.foul.toString())
                    setupScoreClickable(true)
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

                    showWinnerFullscreenDialog(
                        viewModel.athleteRedUpdated,
                        ::fightEnded,
                        ::restartFight
                    )
                }
            }
        }

        viewModel.athleteBlue.observe(viewLifecycleOwner) {
            when (it) {
                is AthleteState.AthleteDefault -> {
                    updateScoreBlue(it.athlete.score.toString())
                    updateFoulBlue(it.athlete.foul.toString())
                    setupScoreClickable(true)
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

                    showWinnerFullscreenDialog(
                        viewModel.athleteBlueUpdated,
                        ::fightEnded,
                        ::restartFight
                    )
                }
            }
        }

        viewModel.fight.observe(viewLifecycleOwner) {
            viewBinding.toolbar.setupParams(title = it.nameFight)
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
    }

    private fun setupScoreboard() {
        viewBinding.apply {
            this.red.setupColor(R.string.red)
            this.blue.setupColor(R.string.blue)
        }
    }

    private fun returnToLastScreen() {
        parentFragmentManager.popBackStackImmediate()
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
            red.setupScoreClickable(value)
            blue.setupScoreClickable(value)
        }
    }

    private fun setupChronometer(minutes: Int, seconds: Int) {
        context?.let {
            setupTime(
                it.getString(minutes),
                it.getString(seconds)
            )
        }
    }

    private fun setupTime(minutes: String, seconds: String) {
        viewBinding.regressiveCounter.setupMinutes(
            minutes,
            seconds
        )
    }

    private fun setupPlayPause() {
        viewBinding.regressiveCounter.apply {
            this.setupPlayPause {
                if (this.isRunning()) {
                    setupRegressiveCounterIsRunning(false)
                    pauseRegressiveCounter()
                } else {
                    setupRegressiveCounterIsRunning(true)
                    viewModel.timerRound?.let { startRegressiveCounter(it) }

                    if (viewModel.shouldStartInterval && !viewModel.shouldStartSecondRound) {
                        this.setupPlayPauseVisibility(false)
                    }
                }
            }
        }
    }

    private fun startRegressiveCounter(timerRound: Long) {
        regressiveCounter = RegressiveCounter(
            time = timerRound,
            regressiveCounterCallback = this
        ).start()
    }

    private fun pauseRegressiveCounter() {
        regressiveCounter.cancel()
    }

    private fun setupRound(round: Int) {
        viewBinding.regressiveCounter.setupRound(round)
    }

    private fun setupTimeRound(value: Long) {
        viewModel.setupTimerRounder(value)
    }

    private fun setupToolbar() {
        viewBinding.toolbar.apply {
            this.setupParams(title = getString(R.string.card_custom_view_title_1))
            this.setupBack { returnToLastScreen() }
        }
    }

    private fun setupRegressiveCounterIsRunning(value: Boolean) {
        viewBinding.regressiveCounter.setupIsRunning(value)
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

    override fun onTick(minutes: String, seconds: String, millisUntilFinished: Long) {
        setupTimeRound(millisUntilFinished)
        setupTime(minutes, seconds)
    }

    override fun finish() {
        viewModel.shouldStartFirstRound = true

        if (viewModel.shouldStartSecondRound) {
            // TODO: To finish fight
        } else {
            if (viewModel.shouldStartInterval) {
                viewModel.shouldStartSecondRound = true
                setupRound(R.string.round_two)
                setupTimeRound(TIME_ROUND)
                setupChronometer(R.string.tree_minutes, R.string.zero_minutes)
            } else {
                viewModel.shouldStartInterval = true
                setupRound(R.string.interval)
                setupTimeRound(TIME_INTERVAL)
                setupChronometer(R.string.zero_minutes, R.string.tree_ten_minutes)
            }
        }

        viewBinding.regressiveCounter.apply {
            setupIsRunning(false)
            setupPlayPauseVisibility(true)
        }
    }

    companion object {
        private const val ARGS = "ARGS"
        private const val TIME_ROUND = 11000L // 10000L - 180000L
        private const val TIME_INTERVAL = 5000L // 5000L - 30000L

        private fun newInstance(fight: Fight) = FightFragment().apply {
            arguments = bundleOf(
                ARGS to fight
            )
        }

        fun show(fightName: Fight) = newInstance(fightName)
    }
}
