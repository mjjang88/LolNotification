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
import androidx.recyclerview.widget.RecyclerView
import com.mjjang.lolnotification.adapter.MatchListAdapter
import com.mjjang.lolnotification.databinding.FragmentMatchListBinding
import com.mjjang.lolnotification.network.RetrofitProc
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

        (binding.listMatch.adapter as MatchListAdapter).registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                checkEmpty()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                checkEmpty()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                checkEmpty()
            }

            fun checkEmpty() {
                binding.textEmptyList.visibility = (if (adapter.itemCount == 0) View.VISIBLE else View.GONE)
            }
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        RetrofitProc.refreshMatch()
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
