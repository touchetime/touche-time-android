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
import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.ui.activity.main.MainActivity
import com.touchetime.presentation.ui.fragments.category.CategoryFragment
import com.touchetime.presentation.ui.fragments.fight.FightFragment
import com.touchetime.presentation.ui.fragments.style.StyleFragment
import com.touchetime.presentation.ui.fragments.weight.WeightFragment

class CustomizeFight : Fragment() {

    private lateinit var viewBinding: FragmentCustomizeFightBinding
    private val viewModel: CustomizeFightViewModel by viewModels()
    private val mainActivity: MainActivity?
        get() = activity as? MainActivity
    private val resultKeys = arrayOf(
        CategoryFragment.CATEGORY_SELECTED,
        StyleFragment.STYLE_SELECTED,
        WeightFragment.WEIGHT_SELECTED
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
        setupGoFight()
        setupResultKeysListeners()
        setupObservers()
    }

    override fun onDestroyView() {
        backPressedCallback.remove()
        super.onDestroyView()
    }

    private fun setupToolbar() {
        viewBinding.toolbar.apply {
            this.setupParams(title = getString(R.string.card_custom_view_title_1))
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
            this.setupIsEnabled(false)
            this.setupListener {
                StyleFragment.show(
                    childFragmentManager,
                    viewModel.styleSelected.value
                )
            }
        }
    }

    private fun setupWeight() {
        viewBinding.weight.apply {
            this.setupParams(
                R.string.chosen_option_view_title_3,
                R.string.chosen_option_view_description_2
            )
            this.setupIsEnabled(false)

            // TODO: Adjust it
            this.setupListener {
                WeightFragment.show(
                    childFragmentManager,
                    Fight(
                        viewModel.fight.nameFight,
                        viewModel.categorySelected.value,
                        viewModel.styleSelected.value,
                        viewModel.weightSelected.value
                    )
                )
            }
        }
    }

    private fun resetWeightSelected() {
        viewModel.setupWeightSelected(null)
    }

    private fun setupGoFight() {
        viewBinding.goFight.setOnClickListener {
            setupFightName()

            navigateToFragment(
                FightFragment.show(viewModel.fight),
                FightFragment::class.java.name
            )
        }
    }

    private fun setupFightName() {
        context?.let { viewModel.setupNameFight(it) }
    }

    private fun navigateToFragment(fragment: Fragment, key: String) {
        mainActivity?.navigateToFragment(fragment, key)
    }

    private fun setupResultKeysListeners() {
        resultKeys.forEach {
            when (it) {
                CategoryFragment.CATEGORY_SELECTED,
                StyleFragment.STYLE_SELECTED,
                WeightFragment.WEIGHT_SELECTED
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

            checkButtonVisibility()
            checkStyleEnabled()
            resetWeightSelected()
            checkWeightEnabled()
        }

        viewModel.styleSelected.observe(viewLifecycleOwner) {
            viewBinding.style.setupItemSelectedVisibility(it)

            checkButtonVisibility()
            resetWeightSelected()
            checkWeightEnabled()
        }

        viewModel.weightSelected.observe(viewLifecycleOwner) {
            viewBinding.weight.setupItemSelectedVisibility(it, true)

            checkButtonVisibility()
        }
    }

    private fun checkButtonVisibility() {
        changeButtonVisibility(
            viewModel.categorySelected.value != null &&
                viewModel.styleSelected.value != null &&
                viewModel.weightSelected.value != null
        )
    }

    private fun checkStyleEnabled() {
        viewBinding.style.setupIsEnabled(
            viewModel.categorySelected.value != null
        )
    }

    private fun checkWeightEnabled() {
        viewBinding.weight.setupIsEnabled(
            viewModel.categorySelected.value != null &&
                viewModel.styleSelected.value != null
        )
    }

    private fun changeButtonVisibility(value: Boolean) {
        viewBinding.goFight.isEnabled = value
    }

    private fun handleResultKey(key: String, bundle: Bundle) {
        when (key) {
            CategoryFragment.CATEGORY_SELECTED -> setupCategorySelected(bundle)
            StyleFragment.STYLE_SELECTED -> setupStyleSelected(bundle)
            WeightFragment.WEIGHT_SELECTED -> setupWeightSelected(bundle)
        }
    }

    private fun setupCategorySelected(bundle: Bundle) {
        (bundle.getSerializable(CategoryFragment.CATEGORY) as? Int)?.let {
            viewModel.setupCategorySelected(it)
        }
    }

    private fun setupStyleSelected(bundle: Bundle) {
        (bundle.getSerializable(StyleFragment.STYLE) as? Int)?.let {
            viewModel.setupStyleSelected(it)
        }
    }

    private fun setupWeightSelected(bundle: Bundle) {
        (bundle.getSerializable(WeightFragment.WEIGHT) as? Int)?.let {
            viewModel.setupWeightSelected(it)
        }
    }

    private fun returnToLastScreen() {
        parentFragmentManager.popBackStackImmediate()
    }

    companion object {
        fun newInstance() = CustomizeFight()
    }
}
