package com.veselovvv.coinsapp.domain.assetmarkets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.Object

sealed class AssetsMarketsDomain : Object<AssetsMarketsUi, AssetsMarketsDomainToUiMapper> {
    data class Success(
        private val assetsMarkets: List<AssetMarketsData>,
        private val assetMarketsMapper: AssetMarketsDataToDomainMapper
    ) : AssetsMarketsDomain() {
        override fun map(mapper: AssetsMarketsDomainToUiMapper) = mapper.map(
            assetsMarkets.map { assetMarkets -> assetMarkets.map(assetMarketsMapper) }
        )
    }

    data class Fail(private val error: ErrorType) : AssetsMarketsDomain() {
        override fun map(mapper: AssetsMarketsDomainToUiMapper) = mapper.map(error)
    }
}
