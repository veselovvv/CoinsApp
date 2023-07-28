package com.veselovvv.coinsapp.presentation.assetinfo

import com.veselovvv.coinsapp.domain.assetinfo.AssetInfoDomainToUiMapper

class BaseAssetInfoDomainToUiMapper : AssetInfoDomainToUiMapper {
    override fun map(
        supply: String,
        maxSupply: String,
        marketCapUsd: String,
        volumeUsd24Hr: String,
        priceUsd: String,
        changePercent24Hr: String,
        vwap24Hr: String
    ) = AssetInfoUi.Base(supply, maxSupply, marketCapUsd, volumeUsd24Hr, priceUsd, changePercent24Hr, vwap24Hr)
}