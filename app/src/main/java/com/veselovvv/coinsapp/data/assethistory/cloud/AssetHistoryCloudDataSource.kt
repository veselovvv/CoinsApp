package com.veselovvv.coinsapp.data.assethistory.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface AssetHistoryCloudDataSource {
    suspend fun fetchAssetHistory(assetId: String): List<AssetHistoryCloud>

    class Base(
        private val service: AssetHistoryService,
        private val gson: Gson
    ) : AssetHistoryCloudDataSource {
        private val type = object : TypeToken<AssetsHistoryCloud>() {}.type

        override suspend fun fetchAssetHistory(assetId: String): List<AssetHistoryCloud> {
            val assetsHistoryCloud: AssetsHistoryCloud =
                gson.fromJson(service.fetchAssetHistory(assetId).string(), type)
            return assetsHistoryCloud.getAssetHistoryList()
        }
    }
}