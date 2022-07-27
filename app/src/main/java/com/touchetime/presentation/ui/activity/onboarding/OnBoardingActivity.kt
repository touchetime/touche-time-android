package com.touchetime.presentation.ui.activity.onboarding

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.touchetime.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : FragmentActivity() {

    private lateinit var viewBinding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupAdapter()
        setupDotsIndicator()
    }

    override fun onBackPressed() {
        viewBinding.viewPager.apply {
            if (this.currentItem == 0) {
                finish()
            } else {
                --this.currentItem
            }
        }
    }

    private fun setupAdapter() {
        viewBinding.viewPager.adapter = OnBoardingAdapter(this)
    }

    private fun setupDotsIndicator() {
        viewBinding.dotsIndicator.setViewPager2(viewBinding.viewPager)
    }
}
