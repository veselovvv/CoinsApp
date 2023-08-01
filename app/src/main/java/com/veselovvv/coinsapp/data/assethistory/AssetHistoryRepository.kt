package com.veselovvv.coinsapp.data.assethistory

import com.veselovvv.coinsapp.data.assethistory.cloud.AssetHistoryCloudDataSource
import com.veselovvv.coinsapp.data.assethistory.cloud.AssetHistoryCloudMapper

interface AssetHistoryRepository {
    suspend fun fetchAssetHistory(assetId: String): AssetsHistoryData

    class Base(
        private val cloudDataSource: AssetHistoryCloudDataSource,
        private val cloudMapper: AssetHistoryCloudMapper
    ) : AssetHistoryRepository {
        override suspend fun fetchAssetHistory(assetId: String) = try {
            val assetsHistoryCloudList = cloudDataSource.fetchAssetHistory(assetId)
            val assetsHistory = cloudMapper.map(assetsHistoryCloudList)
            AssetsHistoryData.Success(assetsHistory)
        } catch (e: Exception) {
            AssetsHistoryData.Fail(e)
        }
    }
}