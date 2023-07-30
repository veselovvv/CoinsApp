package com.veselovvv.coinsapp.data.assets

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.assets.AssetDomain

data class AssetData(
    private val id: String,
    private val rank: String,
    private val symbol: String,
    private val name: String
) : Object<AssetDomain, AssetDataToDomainMapper> {
    override fun map(mapper: AssetDataToDomainMapper) = mapper.map(id, rank, symbol, name)
}