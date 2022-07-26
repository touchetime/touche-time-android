package com.touchetime.presentation.ui.activity.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.touchetime.data.sharedpreferences.OnBoardingSharedPreferences
import com.touchetime.databinding.FragmentDefaultOnBoardingBinding
import com.touchetime.presentation.ui.activity.main.MainActivity

class ScreenTreeOnBoardingFragment : Fragment() {

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

        setupOnBoardingWasVisible()

        viewBinding.button.apply {
            isVisible = true

            setOnClickListener {
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
        }
        viewBinding.text.text = "ccc ccc ccc"
    }

    private fun setupOnBoardingWasVisible() {
        OnBoardingSharedPreferences(requireContext()).wasVisible = true
    }

    companion object {
        fun newInstance() = ScreenTreeOnBoardingFragment()
    }
}
