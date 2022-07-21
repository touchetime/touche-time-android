package com.touchetime.presentation.common

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    open fun returnToLastScreen() {
        parentFragmentManager.popBackStackImmediate()
    }

    open fun finishActivity() {
        activity?.finish()
    }
}
