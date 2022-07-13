package com.touchetime.presentation.ui.fragments.customizefight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.touchetime.R
import com.touchetime.databinding.FragmentCustomizeFightBinding
import com.touchetime.presentation.ui.fragments.category.CategoryFragment

class CustomizeFight : Fragment() {

    private lateinit var viewBinding: FragmentCustomizeFightBinding
    private val viewModel: CustomizeFightViewModel by viewModels()
    private val resultKeys = arrayOf(
        CategoryFragment.CATEGORY_SELECTED
    )
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
        setupResultKeysListeners()
        setupObservers()
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
            this.setupParams(
                R.string.chosen_option_view_title_1,
                R.string.chosen_option_view_description_1
            )
            this.setupListener {
                CategoryFragment.show(
                    childFragmentManager,
                    viewModel.categorySelected.value
                )
            }
        }
    }

    private fun setupStyle() {
        viewBinding.style.apply {
            this.setupParams(
                R.string.chosen_option_view_title_2,
                R.string.chosen_option_view_description_2
            )
            this.setupListener { }
        }
    }

    private fun setupWeight() {
        viewBinding.weight.apply {
            this.setupParams(
                R.string.chosen_option_view_title_3,
                R.string.chosen_option_view_description_3
            )
            this.setupListener { }
        }
    }

    private fun setupResultKeysListeners() {
        resultKeys.forEach {
            when (it) {
                CategoryFragment.CATEGORY_SELECTED,
                -> childFragmentManager
                else -> activity?.supportFragmentManager
            }?.setFragmentResultListener(
                it,
                viewLifecycleOwner,
                ::handleResultKey
            )
        }
    }

    private fun setupObservers() {
        viewModel.categorySelected.observe(viewLifecycleOwner) {
            viewBinding.category.setupItemSelectedVisibility(it)
        }
    }

    private fun handleResultKey(key: String, bundle: Bundle) {
        when (key) {
            CategoryFragment.CATEGORY_SELECTED -> setupCategorySelected(bundle)
        }
    }

    private fun setupCategorySelected(bundle: Bundle) {
        (bundle.getSerializable(CategoryFragment.CATEGORY) as? Int)?.let {
            viewModel.setupCategorySelected(it)
        }
    }

    private fun returnToLastScreen() {
        parentFragmentManager.popBackStackImmediate()
    }

    companion object {
        fun newInstance() = CustomizeFight()
    }
}
