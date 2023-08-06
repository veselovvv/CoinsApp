package com.veselovvv.coinsapp.data.assetmarkets

import com.veselovvv.coinsapp.domain.assetmarkets.AssetMarketsDomain

interface AssetMarketsDataToDomainMapper {
    fun map(exchangeId: String, baseId: String, quoteId: String): AssetMarketsDomain
}