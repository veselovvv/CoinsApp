package com.veselovvv.coinsapp.domain.assets

class BaseAssetDataToDomainMapper : AssetDataToDomainMapper {
    override fun map(rank: String, symbol: String, name: String) =
        AssetDomain(rank, symbol, name)
}