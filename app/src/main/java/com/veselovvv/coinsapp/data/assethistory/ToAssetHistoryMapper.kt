package com.veselovvv.coinsapp.data.assethistory

interface ToAssetHistoryMapper {
    fun map(priceUsd: String, time: Long): AssetHistoryData

    class Base : ToAssetHistoryMapper {
        override fun map(priceUsd: String, time: Long) = AssetHistoryData(priceUsd, time.toString())
    }
}