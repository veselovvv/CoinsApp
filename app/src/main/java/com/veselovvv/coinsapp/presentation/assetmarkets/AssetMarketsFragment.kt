package com.veselovvv.coinsapp.presentation.assetmarkets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isEmpty
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.databinding.FragmentAssetMarketsBinding
import com.veselovvv.coinsapp.presentation.core.BaseFragment
import com.veselovvv.coinsapp.presentation.core.SearchListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetMarketsFragment : BaseFragment<FragmentAssetMarketsBinding>() {
    private val viewModel: AssetsMarketsViewModel by viewModels()
    private lateinit var toolbar: Toolbar

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAssetMarketsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AssetMarketsAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchAssetMarkets(/*todo get id*/)
        },
            object : AssetMarketsAdapter.AssetMarketsListener {
                override fun showAssetMarkets(exchangeId: String, baseId: String, quoteId: String) {
                    viewModel.saveAssetMarketsInfo(exchangeId, baseId, quoteId)
                    //TODO navigate
                }
            }
        )

        toolbar = binding.assetMarketsToolbar
        with(toolbar) {
            title = getString(R.string.asset_markets)
            inflateMenu(R.menu.asset_markets_toolbar_menu)

            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_search_asset_markets -> {
                        (item.actionView as SearchView).apply {
                            queryHint = getString(R.string.search_asset_markets)
                            setOnQueryTextListener(object : SearchListener {
                                override fun find(query: String?): Boolean {
                                    viewModel.searchAssetMarkets(/*todo get id*/, query.toString())
                                    return !query.isNullOrEmpty()
                                }
                            })
                        }
                        true
                    } else -> false
                }
            }
        }

        val swipeToRefreshLayout = binding.assetMarketsSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchAssetMarkets(/*todo id*/)
            swipeToRefreshLayout.isRefreshing = false
        }

        binding.assetMarketsRecyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        viewModel.observe(this) { assetMarketsUiList ->
            adapter.update(assetMarketsUiList)
        }
        viewModel.fetchAssetMarkets(/*todo id*/)
    }

    override fun onStart() {
        super.onStart()
        if (toolbar.menu.isEmpty()) toolbar.inflateMenu(R.menu.asset_markets_toolbar_menu)
    }

    override fun onPause() {
        super.onPause()
        toolbar.menu.clear()
    }
}