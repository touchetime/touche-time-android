package com.touchetime.presentation.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.touchetime.R
import com.touchetime.databinding.FragmentHomeBinding
import com.touchetime.presentation.ui.activity.main.MainActivity
import com.touchetime.presentation.ui.fragments.customizefight.CustomizeFight
import com.touchetime.presentation.ui.fragments.fight.FightFragment

class HomeFragment : Fragment() {

    private lateinit var viewBinding: FragmentHomeBinding
    private val mainActivity: MainActivity?
        get() = activity as? MainActivity
    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity?.finish()
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
                CustomizeFight.newInstance(),
                CustomizeFight::class.java.name
            )
        }
    }

    private fun setupCustomFightListener() {
        viewBinding.customFight.setOnClickListener {
            navigateToFragment(
                FightFragment.newInstance(),
                FightFragment::class.java.name
            )
        }
    }

    private fun navigateToFragment(fragment: Fragment, key: String) {
        mainActivity?.navigateToFragment(
            fragment, key
        )
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
