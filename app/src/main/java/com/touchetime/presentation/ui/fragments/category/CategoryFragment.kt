package com.touchetime.presentation.ui.fragments.category

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
import com.touchetime.presentation.state.CategoryState

class CategoryFragment : BottomSheetDialogTransparentBackgroundFragment() {

    private lateinit var viewBinding: BottomSheetDefaultBinding
    private val viewModel: CategoryViewModel by viewModels()

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
        arguments?.getParcelable<CategoryState>(ARGS)?.let {
            viewModel.setupCategorySelected(it)
        }
    }

    private fun setupBottomSheet() {
        viewBinding.title.setText(R.string.category)
    }

    private fun setupAdapter() {
        val adapter = CategoryAdapter() {
            parentFragmentManager.setFragmentResult(
                CATEGORY_SELECTED,
                bundleOf(
                    CATEGORY to it
                )
            )
            dismiss()
        }

        viewBinding.recyclerView.adapter = adapter

        adapter.submitList(viewModel.getListCategory())
    }

    companion object {
        private const val ARGS = "ARGS"
        const val CATEGORY = "CATEGORY"
        const val CATEGORY_SELECTED = "CATEGORY_SELECTED"

        private fun newInstance(categoryState: CategoryState = CategoryState.DEFAULT) =
            CategoryFragment().apply {
                arguments = bundleOf(
                    ARGS to categoryState
                )
            }

        fun show(
            fragmentManager: FragmentManager,
            categoryState: CategoryState = CategoryState.DEFAULT
        ) =
            newInstance(categoryState).show(fragmentManager, CategoryFragment::class.java.name)
    }
}
