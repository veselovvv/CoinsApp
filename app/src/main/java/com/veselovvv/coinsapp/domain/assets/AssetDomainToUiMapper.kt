package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.presentation.assets.AssetUi

interface AssetDomainToUiMapper {
    fun map(rank: String, symbol: String, name: String): AssetUi
}