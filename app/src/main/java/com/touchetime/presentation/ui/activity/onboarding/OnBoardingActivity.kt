package com.touchetime.presentation.ui.activity.onboarding

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.touchetime.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : FragmentActivity() {

    private lateinit var viewBinding: ActivityOnBoardingBinding

    private val adapter = OnBoardingAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.viewPager.adapter = adapter
        viewBinding.dotsIndicator.setViewPager2(viewBinding.viewPager)
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
}
