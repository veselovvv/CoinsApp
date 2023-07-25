package com.veselovvv.coinsapp.domain.assetinfo

interface AssetInfoDomainToUiMapper {
    fun map(
        supply: String,
        maxSupply: String,
        marketCapUsd: String,
        volumeUsd24Hr: String,
        priceUsd: String,
        changePercent24Hr: String,
        vwap24Hr: String
    ): AssetInfoUi
}