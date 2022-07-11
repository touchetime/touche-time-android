package com.touchetime.presentation.ui.fragments.fight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.touchetime.R
import com.touchetime.databinding.FragmentFightBinding

class FightFragment : Fragment() {

    private lateinit var viewBinding: FragmentFightBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFightBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupScoreboard()
        setupBack()
        setupRed()
        setupBlue()
    }

    private fun setupScoreboard() {
        viewBinding.apply {
            this.red.setupColor(R.string.red)
            this.blue.setupColor(R.string.blue)
        }
    }

    private fun setupBack() {
        viewBinding.back.setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
        }
    }

    private fun setupRed() {
        viewBinding.red.apply {
            this.setupAddScore { }
            this.setupRemoveScore { }
            this.setupTouche { }
            this.setupFoul { }
        }
    }

    private fun setupBlue() {
        viewBinding.blue.apply {
            this.setupAddScore { }
            this.setupRemoveScore { }
            this.setupTouche { }
            this.setupFoul { }
        }
    }

    companion object {
        fun newInstance() = FightFragment()
    }
}
