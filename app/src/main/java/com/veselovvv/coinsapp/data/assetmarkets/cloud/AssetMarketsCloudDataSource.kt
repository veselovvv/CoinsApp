package com.veselovvv.coinsapp.data.assetmarkets.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface AssetMarketsCloudDataSource {
    suspend fun fetchAssetMarkets(assetId: String): List<AssetMarketsCloud>

    class Base(
        private val service: AssetMarketsService,
        private val gson: Gson
    ) : AssetMarketsCloudDataSource {
        private val type = object : TypeToken<AssetsMarketsCloud>() {}.type

        override suspend fun fetchAssetMarkets(assetId: String): List<AssetMarketsCloud> {
            val assetsMarketsCloud: AssetsMarketsCloud =
                gson.fromJson(service.fetchAssetMarkets(assetId).string(), type)

            return assetsMarketsCloud.getAssetMarketsList()
        }
    }
}