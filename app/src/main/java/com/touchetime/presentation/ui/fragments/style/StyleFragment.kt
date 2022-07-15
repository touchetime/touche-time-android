package com.touchetime.presentation.ui.fragments.style

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.touchetime.R
import com.touchetime.databinding.BottomSheetDefaultBinding
import com.touchetime.presentation.common.BottomSheetDialogTransparentBackgroundFragment
import com.touchetime.presentation.state.StyleState

class StyleFragment : BottomSheetDialogTransparentBackgroundFragment() {

    private lateinit var viewBinding: BottomSheetDefaultBinding
    private val viewModel: StyleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = BottomSheetDefaultBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        readArgs()
        setupBottomSheet()
        setupAdapter()
    }

    private fun readArgs() {
        arguments?.getParcelable<StyleState>(ARGS).let {
            viewModel.setupStyleSelected(it)
        }
    }

    private fun setupBottomSheet() {
        viewBinding.title.setText(R.string.style)
    }

    private fun setupAdapter() {
        val adapter = StyleAdapter() {
            parentFragmentManager.setFragmentResult(
                STYLE_SELECTED,
                bundleOf(
                    STYLE to it
                )
            )
            dismiss()
        }

        viewBinding.recyclerView.adapter = adapter

        adapter.submitList(viewModel.getListStyle())
    }

    companion object {
        private const val ARGS = "ARGS"
        const val STYLE = "STYLE"
        const val STYLE_SELECTED = "STYLE_SELECTED"

        private fun newInstance(styleState: StyleState? = null) = StyleFragment().apply {
            arguments = bundleOf(
                ARGS to styleState
            )
        }

        fun show(fragmentManager: FragmentManager, styleState: StyleState? = null) =
            newInstance(styleState).show(fragmentManager, StyleFragment::class.java.name)
    }
}
