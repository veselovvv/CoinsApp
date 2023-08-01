package com.veselovvv.coinsapp.data.assethistory

import com.veselovvv.coinsapp.domain.assethistory.AssetHistoryDomain

interface AssetHistoryDataToDomainMapper {
    fun map(priceUsd: String, time: String): AssetHistoryDomain
}