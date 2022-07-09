package com.touchetime.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.touchetime.R
import com.touchetime.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewBinding: FragmentHomeBinding

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

        setupFight()
        setupFightListener()
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
        }
    }

    companion object {
        fun netInstance() = HomeFragment()
    }
}
