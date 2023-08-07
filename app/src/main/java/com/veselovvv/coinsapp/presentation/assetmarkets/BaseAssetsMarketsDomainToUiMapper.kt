package com.veselovvv.coinsapp.presentation.assetmarkets

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.domain.assetmarkets.AssetMarketsDomain
import com.veselovvv.coinsapp.domain.assetmarkets.AssetMarketsDomainToUiMapper
import com.veselovvv.coinsapp.domain.assetmarkets.AssetsMarketsDomainToUiMapper

class BaseAssetsMarketsDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val assetMarketsMapper: AssetMarketsDomainToUiMapper
) : AssetsMarketsDomainToUiMapper {
    override fun map(assetsMarkets: List<AssetMarketsDomain>) =
        AssetsMarketsUi.Success(assetsMarkets, assetMarketsMapper)

    override fun map(errorType: ErrorType) = AssetsMarketsUi.Fail(
        resourceProvider.getString(
            when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
        )
    )
}