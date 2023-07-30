package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.presentation.assets.AssetUi

interface AssetDomainToUiMapper {
    fun map(id: String, rank: String, symbol: String, name: String): AssetUi
}