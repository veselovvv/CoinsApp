package com.veselovvv.coinsapp.domain.assetinfo

import com.veselovvv.coinsapp.data.assetinfo.AssetInfoDataToDomainMapper

class BaseAssetInfoDataToDomainMapper : AssetInfoDataToDomainMapper {
    override fun map(
        supply: String,
        maxSupply: String,
        marketCapUsd: String,
        volumeUsd24Hr: String,
        priceUsd: String,
        changePercent24Hr: String,
        vwap24Hr: String
    ) = AssetInfoDomain(supply, maxSupply, marketCapUsd, volumeUsd24Hr, priceUsd, changePercent24Hr, vwap24Hr)
}