package com.veselovvv.coinsapp.domain.assetmarkets

import com.veselovvv.coinsapp.core.Object

data class AssetMarketsDomain(
    private val exchangeId: String,
    private val baseId: String,
    private val quoteId: String
) : Object<AssetMarketsUi, AssetMarketsDomainToUiMapper> {
    override fun map(mapper: AssetMarketsDomainToUiMapper) = mapper.map(exchangeId, baseId, quoteId)
}
