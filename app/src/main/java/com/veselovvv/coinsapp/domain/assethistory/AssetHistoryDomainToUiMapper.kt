package com.veselovvv.coinsapp.domain.assethistory

interface AssetHistoryDomainToUiMapper {
    fun map(priceUsd: String, time: String): AssetHistoryUi
}