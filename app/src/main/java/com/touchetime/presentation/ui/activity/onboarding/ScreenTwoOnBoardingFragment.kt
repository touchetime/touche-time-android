package com.touchetime.presentation.ui.activity.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.touchetime.databinding.FragmentDefaultOnBoardingBinding

class ScreenTwoOnBoardingFragment : Fragment() {

    private lateinit var viewBinding: FragmentDefaultOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentDefaultOnBoardingBinding.inflate(
            inflater,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.text.text = "bbb bbb bbb"
    }

    companion object {
        fun newInstance() = ScreenTwoOnBoardingFragment()
    }
}
