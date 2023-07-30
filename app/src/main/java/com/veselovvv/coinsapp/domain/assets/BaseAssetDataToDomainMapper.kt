package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.data.assets.AssetDataToDomainMapper

class BaseAssetDataToDomainMapper : AssetDataToDomainMapper {
    override fun map(id: String, rank: String, symbol: String, name: String) =
        AssetDomain(id, rank, symbol, name)
}