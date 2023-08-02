package com.veselovvv.coinsapp.presentation.assethistory

import com.veselovvv.coinsapp.domain.assethistory.AssetHistoryDomainToUiMapper

class BaseAssetHistoryDomainToUiMapper : AssetHistoryDomainToUiMapper {
    override fun map(priceUsd: String, time: String) = AssetHistoryUi.Base(priceUsd, time)
}