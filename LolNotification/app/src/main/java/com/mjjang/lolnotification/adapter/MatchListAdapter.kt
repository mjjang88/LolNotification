package com.mjjang.lolnotification.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mjjang.lolnotification.data.Match
import com.mjjang.lolnotification.databinding.ListItemMatchBinding

class MatchListAdapter : ListAdapter<Match, RecyclerView.ViewHolder>(MatchDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MatchViewHolder(
            ListItemMatchBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val match = getItem(position)
        (holder as MatchViewHolder).bind(match)
    }

    class MatchViewHolder(
        private val binding : ListItemMatchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {

            }

            binding.checkRecom.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                // 새로 체크가 된 경우
                if (isChecked) {
                    if (binding.checkNotRecom.isChecked) {
                        binding.checkNotRecom.isChecked = false

                        // post checkNotRecom!! --
                    }

                    // post checkRecom!! ++

                    // preference에 id 등록

                } else {

                    // post checkRecom!! --

                    // preference에 id 해제
                }
            })

            binding.checkNotRecom.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                // 새로 체크가 된 경우
                if (isChecked) {
                    if (binding.checkRecom.isChecked) {
                        binding.checkRecom.isChecked = false

                        // post checkRecom!! --
                    }

                    // post checkNotRecom!! ++

                    // preference에 id 등록

                } else {

                    // post checkNotRecom!! --

                    // preference에 id 해제
                }
            })
        }

        fun bind(item: Match) {
            binding.apply {
                match = item
                executePendingBindings()
            }
        }
    }

}

private class MatchDiffCallback : DiffUtil.ItemCallback<Match>() {

    override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
        return oldItem == newItem
    }
}