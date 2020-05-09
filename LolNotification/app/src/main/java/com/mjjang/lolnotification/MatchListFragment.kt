package com.mjjang.lolnotification

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mjjang.lolnotification.adapter.MatchListAdapter
import com.mjjang.lolnotification.databinding.FragmentMatchListBinding
import com.mjjang.lolnotification.utilities.InjectorUtils
import com.mjjang.lolnotification.viewmodels.MatchListViewModel

class MatchListFragment : Fragment() {

    private val viewModel: MatchListViewModel by viewModels {
        InjectorUtils.provideMatchListViewModelFactory(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
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

        binding.listMatch.addItemDecoration(RecyclerViewDecoration(resources.getDimension(R.dimen.margin_normal).toInt()))

        return binding.root
    }

    class RecyclerViewDecoration(
        private val height: Int
    ) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            if (parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount - 1) {
                outRect.bottom = height
            }
        }
    }

    private fun subscribeUi(adapter: MatchListAdapter) {
        viewModel.matchs.observe(viewLifecycleOwner) { matchs ->
            adapter.submitList(matchs)
        }
    }

}
