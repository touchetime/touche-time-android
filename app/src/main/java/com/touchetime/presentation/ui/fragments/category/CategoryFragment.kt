package com.touchetime.presentation.ui.fragments.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.touchetime.databinding.FragmentCategoryBinding
import com.touchetime.presentation.common.BottomSheetDialogTransparentBackgroundFragment
import com.touchetime.presentation.ui.BottomSheetItemAdapter

class CategoryFragment : BottomSheetDialogTransparentBackgroundFragment() {

    private lateinit var viewBinding: FragmentCategoryBinding
    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCategoryBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        readArgs()
        setupAdapter()
    }

    private fun readArgs() {
        (arguments?.getSerializable(ARGS) as? Int).let {
            viewModel.setupCategorySelected(it)
        }
    }

    private fun setupAdapter() {
        val adapter = BottomSheetItemAdapter() {
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

        private fun newInstance(categorySelected: Int? = null) = CategoryFragment().apply {
            arguments = bundleOf(
                ARGS to categorySelected
            )
        }

        fun show(fragmentManager: FragmentManager, categorySelected: Int? = null) =
            newInstance(categorySelected).show(fragmentManager, CategoryFragment::class.java.name)
    }
}
