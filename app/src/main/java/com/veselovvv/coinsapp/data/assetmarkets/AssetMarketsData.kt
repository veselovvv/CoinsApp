package com.veselovvv.coinsapp.data.assetmarkets

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.assetmarkets.AssetMarketsDomain

data class AssetMarketsData(
    private val exchangeId: String,
    private val baseId: String,
    private val quoteId: String
) : Object<AssetMarketsDomain, AssetMarketsDataToDomainMapper> {
    override fun map(mapper: AssetMarketsDataToDomainMapper) = mapper.map(exchangeId, baseId, quoteId)
}
