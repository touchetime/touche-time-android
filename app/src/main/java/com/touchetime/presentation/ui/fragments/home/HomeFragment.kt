package com.touchetime.presentation.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.touchetime.R
import com.touchetime.databinding.FragmentHomeBinding
import com.touchetime.presentation.common.BaseFragment
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.ui.activity.main.MainActivity
import com.touchetime.presentation.ui.fragments.createfight.CreateFightFragment
import com.touchetime.presentation.ui.fragments.fight.FightFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private lateinit var viewBinding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val mainActivity: MainActivity?
        get() = activity as? MainActivity
    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            mainActivity?.finishAffinity()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(backPressedCallback)

        setupCardView()
        setupMainFightListener()
        setupCustomFightListener()
    }

    override fun onDestroyView() {
        backPressedCallback.remove()
        super.onDestroyView()
    }

    private fun setupCardView() {
        viewBinding.mainFight.setupParams(
            R.string.card_custom_view_title_1,
            R.string.card_custom_view_description_1,
            R.drawable.ic_uww
        )

        viewBinding.customFight.setupParams(
            R.string.card_custom_view_title_2,
            R.string.card_custom_view_description_2,
            R.drawable.ic_logo
        )
    }

    private fun setupMainFightListener() {
        viewBinding.mainFight.setOnClickListener {
            navigateToFragment(
                CreateFightFragment.newInstance(),
                CreateFightFragment::class.java.name
            )
        }
    }

    private fun setupCustomFightListener() {
        viewBinding.customFight.setOnClickListener {
            val fight = Fight(
                nameFight = getString(R.string.custom_fight),
                isCustom = true
            )

            navigateToFragment(
                FightFragment.show(fight),
                FightFragment::class.java.name
            )
        }
    }

    private fun navigateToFragment(fragment: Fragment, key: String) {
        mainActivity?.navigateToFragment(
            fragment,
            key
        )
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
