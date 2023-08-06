package com.veselovvv.coinsapp.data.assetmarkets

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.assetmarkets.AssetsMarketsDomain

sealed class AssetsMarketsData : Object<AssetsMarketsDomain, AssetsMarketsDataToDomainMapper> {
    data class Success(private val assetsMarkets: List<AssetMarketsData>) : AssetsMarketsData() {
        override fun map(mapper: AssetsMarketsDataToDomainMapper) = mapper.map(assetsMarkets)
    }

    data class Fail(private val exception: Exception) : AssetsMarketsData() {
        override fun map(mapper: AssetsMarketsDataToDomainMapper) = mapper.map(exception)
    }
}
