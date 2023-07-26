package com.veselovvv.coinsapp.data.assetinfo.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface AssetInfoCloudDataSource {
    suspend fun fetchAssetInfo(id: String): AssetInfoCloud

    class Base(
        private val service: AssetInfoService,
        private val gson: Gson
    ) : AssetInfoCloudDataSource {
        private val type = object : TypeToken<AssetsInfoCloud>() {}.type

        override suspend fun fetchAssetInfo(id: String): AssetInfoCloud {
            val assetsInfoCloud: AssetsInfoCloud = gson.fromJson(service.fetchAssetInfo(id).string(), type)
            return assetsInfoCloud.getAssetInfo()
        }
    }
}