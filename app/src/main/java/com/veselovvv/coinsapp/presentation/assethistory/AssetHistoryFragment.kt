package com.veselovvv.coinsapp.presentation.assethistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.databinding.FragmentAssetHistoryBinding
import com.veselovvv.coinsapp.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetHistoryFragment : BaseFragment<FragmentAssetHistoryBinding>() {
    private val viewModel: AssetsHistoryViewModel by viewModels()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAssetHistoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AssetHistoryAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetchAssetHistory(/*TODO get id*/)
            }
        )

        val swipeToRefreshLayout = binding.assetHistorySwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchAssetHistory(/*TODO get id*/)
            swipeToRefreshLayout.isRefreshing = false
        }

        binding.assetHistoryRecyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        viewModel.observe(this) { assetHistoryUiList ->
            adapter.update(assetHistoryUiList)
        }
        viewModel.fetchAssetHistory(/*TODO get id*/)
    }
}