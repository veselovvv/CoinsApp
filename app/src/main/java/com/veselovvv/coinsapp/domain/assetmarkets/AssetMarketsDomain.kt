package com.veselovvv.coinsapp.domain.assetmarkets

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.presentation.assetmarkets.AssetMarketsUi

data class AssetMarketsDomain(
    private val exchangeId: String,
    private val baseId: String,
    private val quoteId: String
) : Object<AssetMarketsUi, AssetMarketsDomainToUiMapper> {
    override fun map(mapper: AssetMarketsDomainToUiMapper) = mapper.map(exchangeId, baseId, quoteId)
}
