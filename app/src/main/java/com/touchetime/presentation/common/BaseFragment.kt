package com.touchetime.presentation.common

import androidx.fragment.app.Fragment
import com.touchetime.presentation.ui.activity.main.MainActivity
import com.touchetime.presentation.ui.fragments.category.CategoryFragment
import com.touchetime.presentation.ui.fragments.customizefight.CustomizeFightFragment
import com.touchetime.presentation.ui.fragments.home.HomeFragment
import com.touchetime.presentation.ui.fragments.style.StyleFragment
import com.touchetime.presentation.ui.fragments.weight.WeightFragment

open class BaseFragment : Fragment() {

    private val mainActivity: MainActivity?
        get() = activity as? MainActivity

    val resultKeys = arrayOf(
        CategoryFragment.CATEGORY_SELECTED,
        StyleFragment.STYLE_SELECTED,
        WeightFragment.WEIGHT_SELECTED,
        CustomizeFightFragment.FIGHT_UPDATED
    )

    open fun returnToLastScreen() {
        parentFragmentManager.popBackStackImmediate()
    }

    open fun navigateToHome() {
        mainActivity?.navigateToFragment(
            HomeFragment.newInstance(),
            HomeFragment::class.java.name
        )
    }

    open fun finishActivity() {
        activity?.finish()
    }
}
