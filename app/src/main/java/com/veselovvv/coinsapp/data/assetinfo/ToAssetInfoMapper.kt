package com.veselovvv.coinsapp.data.assetinfo

interface ToAssetInfoMapper {
    fun map(
        supply: String,
        maxSupply: String,
        marketCapUsd: String,
        volumeUsd24Hr: String,
        priceUsd: String,
        changePercent24Hr: String,
        vwap24Hr: String
    ): AssetInfoData

    class Base : ToAssetInfoMapper {
        override fun map(
            supply: String,
            maxSupply: String,
            marketCapUsd: String,
            volumeUsd24Hr: String,
            priceUsd: String,
            changePercent24Hr: String,
            vwap24Hr: String
        ) = AssetInfoData(supply, maxSupply, marketCapUsd, volumeUsd24Hr, priceUsd, changePercent24Hr, vwap24Hr)
    }
}