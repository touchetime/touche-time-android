package com.touchetime.presentation.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.touchetime.R
import com.touchetime.databinding.BottomSheetItemBinding
import com.touchetime.presentation.model.ObjectToSelect
import com.touchetime.presentation.util.inflate

class BottomSheetItemAdapter(
    private val onItemClicked: (params: Int) -> Unit
) : ListAdapter<ObjectToSelect, BottomSheetItemAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.bottom_sheet_item), onItemClicked)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        view: View,
        private val onItemClicked: (position: Int) -> Unit,
    ) : RecyclerView.ViewHolder(view) {
        private val viewBinding = BottomSheetItemBinding.bind(view)
        private lateinit var param: ObjectToSelect

        fun bind(params: ObjectToSelect) {
            param = params
            viewBinding.apply {
                this.text.isSelected = params.isSelected
                this.text.setText(params.params)
                background.isSelected = params.isSelected
            }
        }

        init {
            view.setOnClickListener {
                onItemClicked(param.params)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ObjectToSelect>() {
        override fun areItemsTheSame(oldItem: ObjectToSelect, newItem: ObjectToSelect): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ObjectToSelect, newItem: ObjectToSelect): Boolean =
            oldItem == newItem
    }
}
