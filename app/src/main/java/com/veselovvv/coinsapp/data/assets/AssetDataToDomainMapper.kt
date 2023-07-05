package com.veselovvv.coinsapp.data.assets

import com.veselovvv.coinsapp.core.Mapper
import com.veselovvv.coinsapp.domain.assets.AssetDomain

interface AssetDataToDomainMapper : Mapper {
    fun map(rank: String, symbol: String, name: String): AssetDomain
}