package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.data.assets.AssetDataToDomainMapper

class BaseAssetDataToDomainMapper : AssetDataToDomainMapper {
    override fun map(rank: String, symbol: String, name: String) =
        AssetDomain(rank, symbol, name)
}