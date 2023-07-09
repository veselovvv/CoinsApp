package com.veselovvv.coinsapp.presentation.assets

import com.veselovvv.coinsapp.domain.assets.AssetDomainToUiMapper

class BaseAssetDomainToUiMapper : AssetDomainToUiMapper {
    override fun map(rank: String, symbol: String, name: String) = AssetUi.Base(rank, symbol, name)
}