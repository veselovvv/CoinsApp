package com.veselovvv.coinsapp.presentation.rates

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
import com.veselovvv.coinsapp.databinding.FragmentRatesBinding
import com.veselovvv.coinsapp.presentation.core.BaseFragment
import com.veselovvv.coinsapp.presentation.core.SearchListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RatesFragment : BaseFragment<FragmentRatesBinding>() {
    private val viewModel: RatesViewModel by viewModels()
    private lateinit var toolbar: Toolbar

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRatesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RatesAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchRates()
        },
            object : RatesAdapter.RateListener {
                override fun showRate(id: String, symbol: String, rateUsd: String) {
                    viewModel.saveRateInfo(id, symbol, rateUsd)
                    //TODO navigate to fragment with rate details
                }
            }
        )

        toolbar = binding.ratesToolbar
        with(toolbar) {
            title = getString(R.string.rates)
            inflateMenu(R.menu.rates_toolbar_menu)

            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_search_rates -> {
                        (item.actionView as SearchView).apply {
                            queryHint = getString(R.string.search_rates)
                            setOnQueryTextListener(object : SearchListener() {
                                override fun find(query: String?): Boolean {
                                    viewModel.searchRates(query.toString())
                                    return !query.isNullOrEmpty()
                                }
                            })
                        }
                        true
                    } else -> false
                }
            }
        }

        val swipeToRefreshLayout = binding.ratesSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchRates()
            swipeToRefreshLayout.isRefreshing = false
        }

        binding.ratesRecyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        viewModel.observe(this) { rateUiList ->
            adapter.update(rateUiList)
        }
        viewModel.fetchRates()
    }

    override fun onStart() {
        super.onStart()
        if (toolbar.menu.isEmpty()) toolbar.inflateMenu(R.menu.rates_toolbar_menu)
    }

    override fun onPause() {
        super.onPause()
        toolbar.menu.clear()
    }
}