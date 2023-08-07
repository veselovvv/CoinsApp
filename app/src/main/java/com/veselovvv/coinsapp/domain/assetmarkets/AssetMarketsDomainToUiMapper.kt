package com.veselovvv.coinsapp.domain.assetmarkets

import com.veselovvv.coinsapp.presentation.assetmarkets.AssetMarketsUi

interface AssetMarketsDomainToUiMapper {
    fun map(exchangeId: String, baseId: String, quoteId: String): AssetMarketsUi
}