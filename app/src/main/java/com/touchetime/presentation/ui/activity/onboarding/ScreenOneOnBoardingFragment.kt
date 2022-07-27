package com.touchetime.presentation.ui.activity.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.touchetime.databinding.FragmentOnBoardingScreenOneBinding
import com.touchetime.presentation.ui.activity.main.MainActivity

class ScreenOneOnBoardingFragment : Fragment() {

    private lateinit var viewBinding: FragmentOnBoardingScreenOneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentOnBoardingScreenOneBinding.inflate(
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
        viewBinding.skip.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }

    companion object {
        fun newInstance() = ScreenOneOnBoardingFragment()
    }
}
