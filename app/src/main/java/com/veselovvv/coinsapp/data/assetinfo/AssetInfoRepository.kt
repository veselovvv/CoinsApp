package com.veselovvv.coinsapp.data.assetinfo

import com.veselovvv.coinsapp.data.assetinfo.cloud.AssetInfoCloudDataSource
import com.veselovvv.coinsapp.data.assetinfo.cloud.AssetInfoCloudMapper

interface AssetInfoRepository {
    suspend fun fetchAssetInfo(id: String): AssetsInfoData

    class Base(
        private val cloudDataSource: AssetInfoCloudDataSource,
        private val cloudMapper: AssetInfoCloudMapper
    ) : AssetInfoRepository {
        override suspend fun fetchAssetInfo(id: String) = try {
            val assetInfoCloud = cloudDataSource.fetchAssetInfo(id)
            val assetInfoData = cloudMapper.map(assetInfoCloud)
            AssetsInfoData.Success(assetInfoData)
        } catch (e: Exception) {
            AssetsInfoData.Fail(e)
        }
    }
}