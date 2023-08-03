package com.veselovvv.coinsapp.presentation.assetinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.databinding.FragmentAssetInfoBinding
import com.veselovvv.coinsapp.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetInfoFragment : BaseFragment<FragmentAssetInfoBinding>() {
    private val viewModel: AssetsInfoViewModel by viewModels()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAssetInfoBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val swipeToRefreshLayout = binding.assetInfoSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchAssetInfo(viewModel.getAssetId())
            swipeToRefreshLayout.isRefreshing = false
        }

        binding.assetInfoRank.text = viewModel.getAssetRank()
        binding.assetInfoSymbol.text = viewModel.getAssetSymbol()
        binding.assetInfoName.text = viewModel.getAssetName()

        binding.assetInfoHistoryButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(ASSET_ID, viewModel.getAssetId())
            navigateWithArguments(R.id.assetHistoryFragment, bundle)
        }

        binding.assetInfoMarketsButton.setOnClickListener {
            //TODO navigate
        }

        viewModel.observe(this) { ui ->
            ui.setup(binding.assetInfoProgressLayout.root)
            ui.map(
                binding.assetInfoSupply,
                binding.assetInfoMaxSupply,
                binding.assetInfoMarketCapUsd,
                binding.assetInfoVolumeUsd24Hr,
                binding.assetInfoPriceUsd,
                binding.assetInfoChangePercent24Hr,
                binding.assetInfoVwap24Hr
            )
            ui.map(
                binding.assetInfoFailLayout.root,
                binding.assetInfoFailLayout.failMessageTextView,
                binding.assetInfoFailLayout.failTryAgainButton,
                object : Retry {
                    override fun tryAgain() = viewModel.fetchAssetInfo(viewModel.getAssetId())
                }
            )
        }
        viewModel.fetchAssetInfo(viewModel.getAssetId())
    }

    companion object {
        private const val ASSET_ID = "assetId"
    }
}