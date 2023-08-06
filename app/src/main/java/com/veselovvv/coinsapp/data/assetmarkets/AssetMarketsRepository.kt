package com.veselovvv.coinsapp.data.assetmarkets

import com.veselovvv.coinsapp.data.assetmarkets.cloud.AssetMarketsCloudDataSource
import com.veselovvv.coinsapp.data.assetmarkets.cloud.AssetMarketsCloudMapper

interface AssetMarketsRepository {
    suspend fun fetchAssetMarkets(assetId: String): AssetsMarketsData
    suspend fun searchAssetMarkets(assetId: String, query: String): AssetsMarketsData

    class Base(
        private val cloudDataSource: AssetMarketsCloudDataSource,
        private val cloudMapper: AssetMarketsCloudMapper
    ) : AssetMarketsRepository {
        override suspend fun fetchAssetMarkets(assetId: String) = try {
            val assetsMarketsCloudList = cloudDataSource.fetchAssetMarkets(assetId)
            val assetsMarkets = cloudMapper.map(assetsMarketsCloudList)
            AssetsMarketsData.Success(assetsMarkets)
        } catch (e: Exception) {
            AssetsMarketsData.Fail(e)
        }

        override suspend fun searchAssetMarkets(assetId: String, query: String) = try {
            val assetsMarketsCloudList = cloudDataSource.fetchAssetMarkets(assetId).filter {
                assetMarkets -> assetMarkets.exchangeIdStartsWith(query)
            }
            val assetsMarkets = cloudMapper.map(assetsMarketsCloudList)
            AssetsMarketsData.Success(assetsMarkets)
        } catch (e: Exception) {
            AssetsMarketsData.Fail(e)
        }
    }
}