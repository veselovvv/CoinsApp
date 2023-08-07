package com.veselovvv.coinsapp.domain.assetmarkets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.assetmarkets.AssetsMarketsUi

interface AssetsMarketsDomainToUiMapper {
    fun map(assetsMarkets: List<AssetMarketsDomain>): AssetsMarketsUi
    fun map(errorType: ErrorType): AssetsMarketsUi
}