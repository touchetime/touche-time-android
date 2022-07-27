package com.touchetime.presentation.ui.activity.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.touchetime.databinding.FragmentOnBoardingScreenTwoBinding

class ScreenTwoOnBoardingFragment : Fragment() {

    private lateinit var viewBinding: FragmentOnBoardingScreenTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentOnBoardingScreenTwoBinding.inflate(
            inflater,
            container,
            false
        )
        return viewBinding.root
    }

    companion object {
        fun newInstance() = ScreenTwoOnBoardingFragment()
    }
}
