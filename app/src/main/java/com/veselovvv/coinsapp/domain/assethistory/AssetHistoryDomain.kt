package com.veselovvv.coinsapp.domain.assethistory

import com.veselovvv.coinsapp.core.Object

data class AssetHistoryDomain(
    private val priceUsd: String,
    private val time: String
) : Object<AssetHistoryUi, AssetHistoryDomainToUiMapper> {
    override fun map(mapper: AssetHistoryDomainToUiMapper) = mapper.map(priceUsd, time)
}
