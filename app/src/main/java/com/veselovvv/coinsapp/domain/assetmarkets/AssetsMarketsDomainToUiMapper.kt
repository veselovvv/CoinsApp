package com.veselovvv.coinsapp.domain.assetmarkets

import com.veselovvv.coinsapp.core.ErrorType

interface AssetsMarketsDomainToUiMapper {
    fun map(assetsMarkets: List<AssetMarketsDomain>): AssetsMarketsUi
    fun map(errorType: ErrorType): AssetsMarketsUi
}