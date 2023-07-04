package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.core.Object

data class AssetDomain(
    private val rank: String,
    private val symbol: String,
    private val name: String
) : Object<AssetUi, AssetDomainToUiMapper> {
    override fun map(mapper: AssetDomainToUiMapper) = mapper.map(rank, symbol, name)
}
