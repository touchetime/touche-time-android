package com.touchetime.presentation.ui.fragments.customizefight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.touchetime.databinding.FragmentCustomizeFightBinding
import com.touchetime.presentation.common.BottomSheetDialogTransparentBackgroundFragment

class CustomizeFightFragment : BottomSheetDialogTransparentBackgroundFragment() {

    private lateinit var viewBinding: FragmentCustomizeFightBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCustomizeFightBinding.inflate(
            inflater,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        private fun newInstance() = CustomizeFightFragment()

        fun show(fragmentManager: FragmentManager) =
            newInstance().show(fragmentManager, CustomizeFightFragment::class.java.name)
    }
}
