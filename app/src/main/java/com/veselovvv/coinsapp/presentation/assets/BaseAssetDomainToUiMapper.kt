package com.veselovvv.coinsapp.presentation.assets

import com.veselovvv.coinsapp.domain.assets.AssetDomainToUiMapper

class BaseAssetDomainToUiMapper : AssetDomainToUiMapper {
    override fun map(id: String, rank: String, symbol: String, name: String) =
        AssetUi.Base(id, rank, symbol, name)
}