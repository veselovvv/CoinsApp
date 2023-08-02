package com.veselovvv.coinsapp.domain.assethistory

import com.veselovvv.coinsapp.data.assethistory.AssetHistoryDataToDomainMapper

class BaseAssetHistoryDataToDomainMapper : AssetHistoryDataToDomainMapper {
    override fun map(priceUsd: String, time: String) = AssetHistoryDomain(priceUsd, time)
}