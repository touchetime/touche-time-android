package com.touchetime.presentation.ui.fragments.category

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.touchetime.R
import com.touchetime.databinding.BottomSheetItemBinding
import com.touchetime.presentation.model.CategorySelected
import com.touchetime.presentation.state.CategoryState
import com.touchetime.presentation.util.inflate

class CategoryAdapter(
    private val onItemClicked: (enum: CategoryState) -> Unit
) : ListAdapter<CategorySelected, CategoryAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.bottom_sheet_item), onItemClicked)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        view: View,
        private val onItemClicked: (enum: CategoryState) -> Unit,
    ) : RecyclerView.ViewHolder(view) {
        private val viewBinding = BottomSheetItemBinding.bind(view)
        private lateinit var categorySelected: CategorySelected

        fun bind(categorySelected: CategorySelected) {
            this.categorySelected = categorySelected

            viewBinding.apply {
                this.text.isSelected = categorySelected.isSelected
                this.text.setText(categorySelected.enum.value)
                background.isSelected = categorySelected.isSelected
            }
        }

        init {
            view.setOnClickListener {
                onItemClicked(categorySelected.enum)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CategorySelected>() {
        override fun areItemsTheSame(
            oldItem: CategorySelected,
            newItem: CategorySelected
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CategorySelected,
            newItem: CategorySelected
        ): Boolean =
            oldItem == newItem
    }
}
