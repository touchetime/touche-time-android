package com.touchetime.presentation.ui.activity.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.touchetime.R
import com.touchetime.databinding.FragmentOnBoardingScreenTreeBinding
import com.touchetime.extensions.finishOnBoarding

class ScreenTreeOnBoardingFragment : Fragment() {

    private lateinit var viewBinding: FragmentOnBoardingScreenTreeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentOnBoardingScreenTreeBinding.inflate(
            inflater,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFightOne()
        setupFightTwo()
        setupFightTree()
        setupListener()
    }

    private fun setupFightOne() {
        viewBinding.fightOne.apply {
            this.setupNameFight(getString(R.string.fight_name_default_1))
            this.setupScore(10, 0)
        }
    }

    private fun setupFightTwo() {
        viewBinding.fightTwo.apply {
            this.setupNameFight(getString(R.string.custom_fight))
            this.setupScore(3, 5)
        }
    }

    private fun setupFightTree() {
        viewBinding.fightTree.apply {
            this.setupNameFight(getString(R.string.fight_name_default_2))
            this.setupScore(14, 4)
        }
    }

    private fun setupListener() {
        viewBinding.finishOnBoarding.setOnClickListener {
            finishOnBoarding(requireContext())
        }
    }

    companion object {
        fun newInstance() = ScreenTreeOnBoardingFragment()
    }
}
