package com.veselovvv.coinsapp.data.assethistory.cloud

import com.veselovvv.coinsapp.data.assethistory.AssetHistoryData
import com.veselovvv.coinsapp.data.assethistory.ToAssetHistoryMapper

interface AssetHistoryCloudMapper {
    fun map(assetsHistory: List<AssetHistoryCloud>): List<AssetHistoryData>

    class Base(private val assetHistoryMapper: ToAssetHistoryMapper) : AssetHistoryCloudMapper {
        override fun map(assetsHistory: List<AssetHistoryCloud>) = assetsHistory.map { assetHistory ->
            assetHistory.map(assetHistoryMapper)
        }
    }
}