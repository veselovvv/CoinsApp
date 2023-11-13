package com.veselovvv.coinsapp.presentation.markets

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
import com.veselovvv.coinsapp.databinding.FragmentMarketsBinding
import com.veselovvv.coinsapp.presentation.core.BaseFragment
import com.veselovvv.coinsapp.presentation.core.SearchListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarketsFragment : BaseFragment<FragmentMarketsBinding>() {
    private val viewModel: MarketsViewModel by viewModels()
    private lateinit var toolbar: Toolbar

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMarketsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MarketsAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchMarkets()
        })

        toolbar = binding.marketsToolbar
        with(toolbar) {
            title = getString(R.string.markets)
            inflateMenu(R.menu.markets_toolbar_menu)

            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_search_markets -> {
                        (item.actionView as SearchView).apply {
                            queryHint = getString(R.string.search_markets)
                            setOnQueryTextListener(object : SearchListener() {
                                override fun find(query: String?): Boolean {
                                    viewModel.searchMarkets(query.toString())
                                    return !query.isNullOrEmpty()
                                }
                            })
                        }
                        true
                    } else -> false
                }
            }
        }

        val swipeToRefreshLayout = binding.marketsSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchMarkets()
            swipeToRefreshLayout.isRefreshing = false
        }

        binding.marketsRecyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        viewModel.observe(this) { marketUiList ->
            adapter.update(marketUiList)
        }
        viewModel.fetchMarkets()
    }

    override fun onStart() {
        super.onStart()
        if (toolbar.menu.isEmpty()) toolbar.inflateMenu(R.menu.markets_toolbar_menu)
    }

    override fun onPause() {
        super.onPause()
        toolbar.menu.clear()
    }
}