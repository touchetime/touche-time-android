package com.touchetime.presentation.ui.fragments.style

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.touchetime.R
import com.touchetime.databinding.BottomSheetItemBinding
import com.touchetime.extensions.inflate
import com.touchetime.presentation.model.StyleSelected
import com.touchetime.presentation.state.StyleState

class StyleAdapter(
    private val onItemClicked: (enum: StyleState) -> Unit
) : ListAdapter<StyleSelected, StyleAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.bottom_sheet_item), onItemClicked)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        view: View,
        private val onItemClicked: (enum: StyleState) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val viewBinding = BottomSheetItemBinding.bind(view)
        private lateinit var styleSelected: StyleSelected

        fun bind(styleSelected: StyleSelected) {
            this.styleSelected = styleSelected

            viewBinding.apply {
                this.text.isSelected = styleSelected.isSelected
                this.text.setText(styleSelected.enum.value)
                background.isSelected = styleSelected.isSelected
            }
        }

        init {
            view.setOnClickListener {
                onItemClicked(styleSelected.enum)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<StyleSelected>() {
        override fun areItemsTheSame(
            oldItem: StyleSelected,
            newItem: StyleSelected
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: StyleSelected,
            newItem: StyleSelected
        ): Boolean =
            oldItem == newItem
    }
}
