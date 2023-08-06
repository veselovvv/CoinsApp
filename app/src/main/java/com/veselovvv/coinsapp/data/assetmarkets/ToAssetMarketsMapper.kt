package com.veselovvv.coinsapp.data.assetmarkets

interface ToAssetMarketsMapper {
    fun map(exchangeId: String, baseId: String, quoteId: String): AssetMarketsData

    class Base : ToAssetMarketsMapper {
        override fun map(exchangeId: String, baseId: String, quoteId: String) =
            AssetMarketsData(exchangeId, baseId, quoteId)
    }
}