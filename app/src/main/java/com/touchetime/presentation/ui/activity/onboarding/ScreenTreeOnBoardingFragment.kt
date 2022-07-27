package com.touchetime.presentation.ui.activity.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.touchetime.data.sharedpreferences.OnBoardingSharedPreferences
import com.touchetime.databinding.FragmentOnBoardingScreenTreeBinding
import com.touchetime.presentation.ui.activity.main.MainActivity

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

        setupListener()
    }

    private fun setupListener() {
        viewBinding.finishOnBoarding.setOnClickListener {
            setupOnBoardingWasVisible()
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }

    private fun setupOnBoardingWasVisible() {
        OnBoardingSharedPreferences(requireContext()).wasVisible = true
    }

    companion object {
        fun newInstance() = ScreenTreeOnBoardingFragment()
    }
}
