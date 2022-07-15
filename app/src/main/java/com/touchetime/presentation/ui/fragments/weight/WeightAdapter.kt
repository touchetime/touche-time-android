package com.touchetime.presentation.ui.fragments.weight

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.touchetime.R
import com.touchetime.databinding.BottomSheetItemBinding
import com.touchetime.presentation.model.WeightSelect
import com.touchetime.presentation.util.inflate

class WeightAdapter(
    private val onItemClicked: (params: Int) -> Unit
) : ListAdapter<WeightSelect, WeightAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.bottom_sheet_item), onItemClicked)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            getItem(position)
        )
    }

    inner class ViewHolder(
        view: View,
        private val onItemClicked: (position: Int) -> Unit,
    ) : RecyclerView.ViewHolder(view) {
        private val viewBinding = BottomSheetItemBinding.bind(view)
        private lateinit var param: WeightSelect

        fun bind(weightSelected: WeightSelect) {
            param = weightSelected
            viewBinding.apply {
                this.text.isSelected = weightSelected.isSelected
                this.background.isSelected = weightSelected.isSelected
                this.text.text = if (weightSelected.value == 0) {
                    "+${currentList[currentList.size - 2].value}kg"
                } else {
                    "-${weightSelected.value}Kg"
                }
            }
        }

        init {
            view.setOnClickListener {
                onItemClicked(param.value)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<WeightSelect>() {
        override fun areItemsTheSame(oldItem: WeightSelect, newItem: WeightSelect): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: WeightSelect, newItem: WeightSelect): Boolean =
            oldItem == newItem
    }
}
