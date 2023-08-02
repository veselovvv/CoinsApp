package com.veselovvv.coinsapp.domain.assethistory

import com.veselovvv.coinsapp.presentation.assethistory.AssetHistoryUi

interface AssetHistoryDomainToUiMapper {
    fun map(priceUsd: String, time: String): AssetHistoryUi
}