package com.mjjang.lolnotification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mjjang.lolnotification.adapter.MatchListAdapter
import com.mjjang.lolnotification.databinding.FragmentMatchListBinding

class MatchListFragment : Fragment() {
    
    // 뷰모델 구현 필요

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMatchListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = MatchListAdapter()
        binding.listMatch.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: MatchListAdapter) {
        TODO("observe 구현필요")
    }

}
