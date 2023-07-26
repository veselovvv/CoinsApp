package com.veselovvv.coinsapp.data.assetinfo

import com.veselovvv.coinsapp.domain.assetinfo.AssetInfoDomain

interface AssetInfoDataToDomainMapper {
    fun map(
        supply: String,
        maxSupply: String,
        marketCapUsd: String,
        volumeUsd24Hr: String,
        priceUsd: String,
        changePercent24Hr: String,
        vwap24Hr: String
    ): AssetInfoDomain
}