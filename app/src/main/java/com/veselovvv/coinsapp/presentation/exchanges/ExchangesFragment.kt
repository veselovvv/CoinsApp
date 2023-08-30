package com.veselovvv.coinsapp.presentation.exchanges

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
import com.veselovvv.coinsapp.databinding.FragmentExchangesBinding
import com.veselovvv.coinsapp.presentation.core.BaseFragment
import com.veselovvv.coinsapp.presentation.core.SearchListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExchangesFragment : BaseFragment<FragmentExchangesBinding>() {
    private val viewModel: ExchangesViewModel by viewModels()
    private lateinit var toolbar: Toolbar

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentExchangesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ExchangesAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchExchanges()
        },
            object : ExchangesAdapter.ExchangeListener {
                override fun showExchange(id: String, name: String, rank: String) {
                    viewModel.saveExchangeInfo(id, name, rank)
                    //TODO navigate to exchange info fragment
                }
            }
        )

        toolbar = binding.exchangesToolbar
        with(toolbar) {
            title = getString(R.string.exchanges)
            inflateMenu(R.menu.exchanges_toolbar_menu)

            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_search_exchanges -> {
                        (item.actionView as SearchView).apply {
                            queryHint = getString(R.string.search_exchanges)
                            setOnQueryTextListener(object : SearchListener() {
                                override fun find(query: String?): Boolean {
                                    viewModel.searchExchanges(query.toString())
                                    return !query.isNullOrEmpty()
                                }
                            })
                        }
                        true
                    } else -> false
                }
            }
        }

        val swipeToRefreshLayout = binding.exchangesSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchExchanges()
            swipeToRefreshLayout.isRefreshing = false
        }

        binding.exchangesRecyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        viewModel.observe(this) { exchangeUiList ->
            adapter.update(exchangeUiList)
        }
        viewModel.fetchExchanges()
    }

    override fun onStart() {
        super.onStart()
        if (toolbar.menu.isEmpty()) toolbar.inflateMenu(R.menu.exchanges_toolbar_menu)
    }

    override fun onPause() {
        super.onPause()
        toolbar.menu.clear()
    }
}