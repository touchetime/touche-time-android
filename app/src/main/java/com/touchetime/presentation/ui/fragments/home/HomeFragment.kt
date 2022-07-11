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

        setupFight()
        setupFightListener()
    }

    override fun onDestroyView() {
        backPressedCallback.remove()
        super.onDestroyView()
    }

    private fun setupFight() {
        viewBinding.fight.setupParams(
            R.string.card_custom_view_title_1,
            R.string.card_custom_view_description_1,
            R.drawable.ic_users
        )
    }

    private fun setupFightListener() {
        viewBinding.fight.setOnClickListener {
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
