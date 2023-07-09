package com.veselovvv.coinsapp.presentation.assets

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
import com.veselovvv.coinsapp.databinding.FragmentAssetsBinding
import com.veselovvv.coinsapp.presentation.core.BaseFragment
import com.veselovvv.coinsapp.presentation.core.SearchListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetsFragment : BaseFragment<FragmentAssetsBinding>() {
    private val viewModel: AssetsViewModel by viewModels()
    private lateinit var toolbar: Toolbar

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAssetsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AssetsAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchAssets()
        },
            object : AssetsAdapter.AssetListener {
                override fun showAsset(rank: String, symbol: String, name: String) {
                    viewModel.saveAssetInfo(rank, symbol, name)
                    //TODO navigate(R.id.)
                }
            }
        )

        toolbar = binding.assetsToolbar
        with(toolbar) {
            title = getString(R.string.assets)
            inflateMenu(R.menu.assets_toolbar_menu)

            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_search_assets -> {
                        (item.actionView as SearchView).apply {
                            queryHint = getString(R.string.search_assets)
                            setOnQueryTextListener(object : SearchListener() {
                                override fun find(query: String?): Boolean {
                                    viewModel.searchAssets(query.toString())
                                    return !query.isNullOrEmpty()
                                }
                            })
                        }
                        true
                    } else -> false
                }
            }
        }

        val swipeToRefreshLayout = binding.assetsSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchAssets()
            swipeToRefreshLayout.isRefreshing = false
        }

        binding.assetsRecyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        viewModel.observe(this) { assetUiList ->
            adapter.update(assetUiList)
        }
        viewModel.fetchAssets()
    }

    override fun onStart() {
        super.onStart()
        if (toolbar.menu.isEmpty()) toolbar.inflateMenu(R.menu.assets_toolbar_menu)
    }

    override fun onPause() {
        super.onPause()
        toolbar.menu.clear()
    }
}