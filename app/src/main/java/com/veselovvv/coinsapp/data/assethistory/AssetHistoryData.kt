package com.veselovvv.coinsapp.data.assethistory

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.assethistory.AssetHistoryDomain

data class AssetHistoryData(
    private val priceUsd: String,
    private val time: String
) : Object<AssetHistoryDomain, AssetHistoryDataToDomainMapper> {
    override fun map(mapper: AssetHistoryDataToDomainMapper) = mapper.map(priceUsd, time)
}
