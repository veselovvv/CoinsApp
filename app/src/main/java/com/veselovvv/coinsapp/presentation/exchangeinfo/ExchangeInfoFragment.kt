package com.veselovvv.coinsapp.presentation.exchangeinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.databinding.FragmentExchangeInfoBinding
import com.veselovvv.coinsapp.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExchangeInfoFragment : BaseFragment<FragmentExchangeInfoBinding>() {
    private val viewModel: ExchangesInfoViewModel by viewModels()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentExchangeInfoBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val swipeToRefreshLayout = binding.exchangeInfoSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchExchangeInfo(viewModel.getExchangeId())
            swipeToRefreshLayout.isRefreshing = false
        }

        with(binding) {
            exchangeInfoName.text = viewModel.getExchangeName()
            exchangeInfoRank.text = viewModel.getExchangeRank()
        }

        viewModel.observe(this) { ui ->
            ui.setup(binding.exchangeInfoProgressLayout.root)
            ui.map(
                binding.exchangeInfoPercentTotalVolume,
                binding.exchangeInfoVolumeUsd,
                binding.exchangeInfoTradingPairs,
                binding.exchangeInfoExchangeUrl
            )
            ui.map(
                binding.exchangeInfoFailLayout.root,
                binding.exchangeInfoFailLayout.failMessageTextView,
                binding.exchangeInfoFailLayout.failTryAgainButton,
                object : Retry {
                    override fun tryAgain() = viewModel.fetchExchangeInfo(viewModel.getExchangeId())
                }
            )
        }
        viewModel.fetchExchangeInfo(viewModel.getExchangeId())
    }
}