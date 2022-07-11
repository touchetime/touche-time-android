package com.touchetime.presentation.ui.fragments.fight

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.touchetime.R
import com.touchetime.databinding.FragmentFightBinding
import com.touchetime.presentation.common.RegressiveCounter

class FightFragment : Fragment(), RegressiveCounter.RegressiveCounterCallback {

    private lateinit var viewBinding: FragmentFightBinding
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

        setupTimeRound(TIME_ROUND)
        setupScoreboard()
        setupBack()
        setupRed()
        setupBlue()
        setupChronometer(
            R.string.tree_minutes,
            R.string.zero_minutes
        )
        setupPlayPause()
    }

    private fun setupScoreboard() {
        viewBinding.apply {
            this.red.setupColor(R.string.red)
            this.blue.setupColor(R.string.blue)
        }
    }

    private fun setupBack() {
        viewBinding.back.setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
        }
    }

    private fun setupRed() {
        viewBinding.red.apply {
            this.setupAddScore { viewModel.addScoreRed() }
            this.setupRemoveScore { viewModel.removeScoreRed() }
            this.setupTouche { viewModel.setupToucheRed() }
            this.setupFoul { viewModel.setupFoulRed() }
        }
    }

    private fun setupBlue() {
        viewBinding.blue.apply {
            this.setupAddScore { viewModel.addScoreBlue() }
            this.setupRemoveScore { viewModel.removeScoreBlue() }
            this.setupTouche { viewModel.setupToucheBlue() }
            this.setupFoul { viewModel.setupFoulBlue() }
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
            minutes, seconds
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

    private fun setupRegressiveCounterIsRunning(value: Boolean) {
        viewBinding.regressiveCounter.setupIsRunning(value)
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
        private const val TIME_ROUND = 11000L // 10000L - 180000L
        private const val TIME_INTERVAL = 5000L // 5000L - 30000L

        fun newInstance() = FightFragment()
    }
}
