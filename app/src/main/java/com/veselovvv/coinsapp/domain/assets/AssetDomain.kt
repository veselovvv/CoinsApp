package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.presentation.assets.AssetUi

data class AssetDomain(
    private val id: String,
    private val rank: String,
    private val symbol: String,
    private val name: String
) : Object<AssetUi, AssetDomainToUiMapper> {
    override fun map(mapper: AssetDomainToUiMapper) = mapper.map(id, rank, symbol, name)
}
