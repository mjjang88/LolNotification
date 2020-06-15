package com.mjjang.lolnotification.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mjjang.lolnotification.data.Match
import com.mjjang.lolnotification.databinding.ListItemMatchBinding
import com.mjjang.lolnotification.manager.App
import com.mjjang.lolnotification.manager.AppPreference
import com.mjjang.lolnotification.network.RetrofitProc

class MatchListAdapter() : ListAdapter<Match, RecyclerView.ViewHolder>(MatchDiffCallback()) {

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

            binding.checkRecom.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    binding.match?.id?.let {
                        AppPreference.putRecomChecked(
                            it,
                            AppPreference.CHECKED
                        )
                    }
                } else {
                    binding.match?.id?.let {
                        AppPreference.putRecomChecked(
                            it,
                            AppPreference.NOT_CHECKED
                        )
                    }
                }
            }

            binding.checkNotRecom.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    binding.match?.id?.let {
                        AppPreference.putNotRecomChecked(
                            it,
                            AppPreference.CHECKED
                        )
                    }
                } else {
                    binding.match?.id?.let {
                        AppPreference.putNotRecomChecked(
                            it,
                            AppPreference.NOT_CHECKED
                        )
                    }
                }
            }

            binding.checkRecom.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_UP -> {
                        if (v is CheckBox) {
                            if (v.isChecked) {
                                RetrofitProc.decRecomMatch(binding.match?.id)
                            } else {
                                RetrofitProc.incRecomMatch(binding.match?.id)
                            }
                        }
                    }
                }
                return@setOnTouchListener false
            }

            binding.checkNotRecom.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_UP -> {
                        if (v is CheckBox) {
                            if (v.isChecked) {
                                RetrofitProc.decNotRecomMatch(binding.match?.id)
                            } else {
                                RetrofitProc.incNotRecomMatch(binding.match?.id)
                            }
                        }
                    }
                }
                return@setOnTouchListener false
            }

            binding.checkRecom.setOnClickListener {
                it.isClickable = false
            }

            binding.checkNotRecom.setOnClickListener {
                it.isClickable = false
            }

            binding.btnYoutubeLink.setOnClickListener {
                if (binding.match != null && !binding.match!!.YouTubeLink.isNullOrEmpty()) {
                    it.context.startActivity(
                        Intent(Intent.ACTION_VIEW)
                            .setData(Uri.parse(binding.match!!.YouTubeLink))
                            .setPackage("com.google.android.youtube")
                    )
                }
            }

            binding.btnNaverLink.setOnClickListener {
                if (binding.match != null && !binding.match!!.NaverLink.isNullOrEmpty()) {
                    it.context.startActivity(
                        Intent(Intent.ACTION_VIEW)
                            .setData(Uri.parse(binding.match!!.NaverLink))
                    )
                }
            }

            binding.btnAccept.setOnClickListener {
                binding.match?.let {
                    RetrofitProc.updateMatchData(
                        it.id,
                        binding.editYoutubeLink.text.toString(),
                        binding.editNaverLink.text.toString(),
                        binding.editRecom.text.toString()
                    )
                }
            }
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