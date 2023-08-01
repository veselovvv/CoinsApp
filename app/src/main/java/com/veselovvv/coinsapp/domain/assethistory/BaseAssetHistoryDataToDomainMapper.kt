package com.veselovvv.coinsapp.domain.assethistory

class BaseAssetHistoryDataToDomainMapper : AssetHistoryDataToDomainMapper {
    override fun map(priceUsd: String, time: String) = AssetHistoryDomain(priceUsd, time)
}