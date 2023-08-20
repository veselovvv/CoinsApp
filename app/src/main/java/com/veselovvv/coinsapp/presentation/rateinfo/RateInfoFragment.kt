package com.veselovvv.coinsapp.presentation.rateinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.databinding.FragmentRateInfoBinding
import com.veselovvv.coinsapp.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RateInfoFragment : BaseFragment<FragmentRateInfoBinding>() {
    private val viewModel: RatesInfoViewModel by viewModels()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRateInfoBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val swipeToRefreshLayout = binding.rateInfoSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchRateInfo(viewModel.getRateId())
            swipeToRefreshLayout.isRefreshing = false
        }

        binding.rateInfoSymbol.text = viewModel.getRateSymbol()
        binding.rateInfoName.text = viewModel.getRateId()
        binding.rateInfoRateUsd.text = viewModel.getRateRateUsd()

        viewModel.observe(this) { ui ->
            ui.setup(binding.rateInfoProgressLayout.root)
            ui.map(
                binding.rateInfoCurrencySymbol,
                binding.rateInfoType
            )
            ui.map(
                binding.rateInfoFailLayout.root,
                binding.rateInfoFailLayout.failMessageTextView,
                binding.rateInfoFailLayout.failTryAgainButton,
                object : Retry {
                    override fun tryAgain() = viewModel.fetchRateInfo(viewModel.getRateId())
                }
            )
        }
        viewModel.fetchRateInfo(viewModel.getRateId())
    }
}