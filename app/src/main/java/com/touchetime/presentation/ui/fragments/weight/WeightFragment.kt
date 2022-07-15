package com.touchetime.presentation.ui.fragments.weight

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
import com.touchetime.presentation.model.Fight

class WeightFragment : BottomSheetDialogTransparentBackgroundFragment() {

    private lateinit var viewBinding: BottomSheetDefaultBinding
    private val viewModel: WeightViewModel by viewModels()

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
        arguments?.getParcelable<Fight>(ARGS)?.let {
            viewModel.setupFight(it)
        }
    }

    private fun setupBottomSheet() {
        viewBinding.title.setText(R.string.weight)
    }

    private fun setupAdapter() {
        val adapter = WeightAdapter() {
            parentFragmentManager.setFragmentResult(
                WEIGHT_SELECTED,
                bundleOf(
                    WEIGHT to it
                )
            )
            dismiss()
        }

        viewBinding.recyclerView.adapter = adapter

        adapter.submitList(viewModel.getListWeight())
    }

    companion object {
        private const val ARGS = "ARGS"
        const val WEIGHT = "WEIGHT"
        const val WEIGHT_SELECTED = "WEIGHT_SELECTED"

        private fun newInstance(
            fight: Fight? = null
        ) = WeightFragment()
            .apply {
                arguments = bundleOf(
                    ARGS to fight
                )
            }

        fun show(
            fragmentManager: FragmentManager,
            fight: Fight? = null
        ) = newInstance(fight).show(
            fragmentManager,
            WeightFragment::class.java.name
        )
    }
}
