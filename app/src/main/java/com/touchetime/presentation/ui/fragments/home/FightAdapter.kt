package com.touchetime.presentation.ui.fragments.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.touchetime.R
import com.touchetime.databinding.FightRecyclerViewItemBinding
import com.touchetime.extensions.inflate
import com.touchetime.presentation.model.FightFinish

class FightAdapter : ListAdapter<FightFinish, FightAdapter.FightViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FightAdapter.FightViewHolder =
        FightViewHolder(parent.inflate(R.layout.fight_recycler_view_item))

    override fun onBindViewHolder(holder: FightAdapter.FightViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FightViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding = FightRecyclerViewItemBinding.bind(itemView)

        fun bind(finish: FightFinish) {
            viewBinding.fightCustomView.apply {
                this.setupNameFight(finish.name)
                this.setupScore(finish.athleteRed.score, finish.athleteBlue.score)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<FightFinish>() {
        override fun areItemsTheSame(oldItem: FightFinish, newItem: FightFinish): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FightFinish, newItem: FightFinish): Boolean =
            oldItem == newItem
    }
}
