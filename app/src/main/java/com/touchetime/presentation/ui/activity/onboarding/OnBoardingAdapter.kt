package com.touchetime.presentation.ui.activity.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = arrayListOf(
        ScreenOneOnBoardingFragment.newInstance(),
        ScreenTwoOnBoardingFragment.newInstance(),
        ScreenTreeOnBoardingFragment.newInstance()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
