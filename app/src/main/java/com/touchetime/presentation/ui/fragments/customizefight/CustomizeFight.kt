package com.touchetime.presentation.ui.fragments.customizefight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.touchetime.R
import com.touchetime.databinding.FragmentCustomizeFightBinding

class CustomizeFight : Fragment() {

    private lateinit var viewBinding: FragmentCustomizeFightBinding
    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            returnToLastScreen()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCustomizeFightBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(backPressedCallback)

        setupToolbar()
        setupCategory()
        setupStyle()
        setupWeight()
    }

    override fun onDestroyView() {
        backPressedCallback.remove()
        super.onDestroyView()
    }

    private fun setupToolbar() {
        viewBinding.toolbar.apply {
            this.setupParams(R.string.card_custom_view_title_1, R.drawable.ic_arrow_left)
            this.setupBack { returnToLastScreen() }
        }
    }

    private fun setupCategory() {
        viewBinding.category.apply {
            this.setupParams(R.string.chosen_option_view_title_1, R.string.chosen_option_view_description_1)
            this.setupListener {  }
        }
    }

    private fun setupStyle() {
        viewBinding.style.apply {
            this.setupParams(R.string.chosen_option_view_title_2, R.string.chosen_option_view_description_2)
            this.setupListener {  }
        }
    }

    private fun setupWeight() {
        viewBinding.weight.apply {
            this.setupParams(R.string.chosen_option_view_title_3, R.string.chosen_option_view_description_3)
            this.setupListener {  }
        }
    }

    private fun returnToLastScreen() {
        parentFragmentManager.popBackStackImmediate()
    }

    companion object {
        fun newInstance() = CustomizeFight()
    }
}
