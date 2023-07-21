package com.veselovvv.coinsapp.data.assets

import com.veselovvv.coinsapp.domain.assets.AssetDomain

interface AssetDataToDomainMapper {
    fun map(rank: String, symbol: String, name: String): AssetDomain
}