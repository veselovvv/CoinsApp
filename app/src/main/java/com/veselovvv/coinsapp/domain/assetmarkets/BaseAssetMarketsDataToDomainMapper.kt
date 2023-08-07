package com.veselovvv.coinsapp.domain.assetmarkets

import com.veselovvv.coinsapp.data.assetmarkets.AssetMarketsDataToDomainMapper

class BaseAssetMarketsDataToDomainMapper : AssetMarketsDataToDomainMapper {
    override fun map(exchangeId: String, baseId: String, quoteId: String) =
        AssetMarketsDomain(exchangeId, baseId, quoteId)
}