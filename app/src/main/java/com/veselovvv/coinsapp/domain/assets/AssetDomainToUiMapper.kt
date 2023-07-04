package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.core.Mapper

interface AssetDomainToUiMapper : Mapper {
    fun map(rank: String, symbol: String, name: String): AssetUi
}