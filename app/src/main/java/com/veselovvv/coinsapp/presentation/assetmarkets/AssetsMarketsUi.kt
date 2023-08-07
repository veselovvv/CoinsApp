package com.veselovvv.coinsapp.presentation.assetmarkets

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.assetmarkets.AssetMarketsDomain
import com.veselovvv.coinsapp.domain.assetmarkets.AssetMarketsDomainToUiMapper

sealed class AssetsMarketsUi : Object<Unit, AssetsMarketsCommunication> {
    data class Success(
        private val assetsMarkets: List<AssetMarketsDomain>,
        private val assetMarketsMapper: AssetMarketsDomainToUiMapper
    ) : AssetsMarketsUi() {
        override fun map(mapper: AssetsMarketsCommunication) {
            if (assetsMarkets.isEmpty())
                mapper.map(listOf(AssetMarketsUi.NoResults))
            else {
                val assetsMarketsUi = assetsMarkets.map { assetMarkets ->
                    assetMarkets.map(assetMarketsMapper)
                }
                mapper.map(assetsMarketsUi)
            }
        }
    }

    data class Fail(private val errorMessage: String) : AssetsMarketsUi() {
        override fun map(mapper: AssetsMarketsCommunication) =
            mapper.map(listOf(AssetMarketsUi.Fail(errorMessage)))
    }
}
