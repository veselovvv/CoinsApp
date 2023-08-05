package com.veselovvv.coinsapp.domain.assetmarkets

interface AssetMarketsDomainToUiMapper {
    fun map(exchangeId: String, baseId: String, quoteId: String): AssetMarketsUi
}