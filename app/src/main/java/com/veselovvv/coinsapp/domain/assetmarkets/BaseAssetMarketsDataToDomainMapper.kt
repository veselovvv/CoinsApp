package com.veselovvv.coinsapp.domain.assetmarkets

class BaseAssetMarketsDataToDomainMapper : AssetMarketsDataToDomainMapper {
    override fun map(exchangeId: String, baseId: String, quoteId: String) =
        AssetMarketsDomain(exchangeId, baseId, quoteId)
}