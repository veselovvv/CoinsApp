package com.veselovvv.coinsapp.data.assets.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface AssetsCloudDataSource {
    suspend fun fetchAssets(): List<AssetCloud>

    class Base(
        private val service: AssetsService,
        private val gson: Gson
    ) : AssetsCloudDataSource {
        private val type = object : TypeToken<AssetsCloud>() {}.type

        override suspend fun fetchAssets(): List<AssetCloud> {
            val assetsCloud: AssetsCloud = gson.fromJson(service.fetchAssets().string(), type)
            return assetsCloud.getAssetsList()
        }
    }
}