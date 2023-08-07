package com.veselovvv.coinsapp.presentation.assetmarkets

import com.veselovvv.coinsapp.domain.assetmarkets.AssetMarketsDomainToUiMapper

class BaseAssetMarketsDomainToUiMapper : AssetMarketsDomainToUiMapper {
    override fun map(exchangeId: String, baseId: String, quoteId: String) =
        AssetMarketsUi.Base(exchangeId, baseId, quoteId)
}