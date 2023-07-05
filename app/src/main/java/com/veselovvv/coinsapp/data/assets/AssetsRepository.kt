package com.veselovvv.coinsapp.data.assets

import com.veselovvv.coinsapp.data.assets.cloud.AssetsCloudDataSource
import com.veselovvv.coinsapp.data.assets.cloud.AssetsCloudMapper

interface AssetsRepository {
    suspend fun fetchAssets(): AssetsData
    suspend fun searchAssets(query: String): AssetsData

    class Base(
        private val cloudDataSource: AssetsCloudDataSource,
        private val cloudMapper: AssetsCloudMapper
    ) : AssetsRepository {
        override suspend fun fetchAssets() = try {
            val assetsCloudList = cloudDataSource.fetchAssets()
            val assets = cloudMapper.map(assetsCloudList)
            AssetsData.Success(assets)
        } catch (e: Exception) {
            AssetsData.Fail(e)
        }

        override suspend fun searchAssets(query: String) = try {
            val foundAssetsCloudList = cloudDataSource.fetchAssets().filter { asset ->
                asset.nameStartsWith(query)
            }

            val assets = cloudMapper.map(foundAssetsCloudList)
            AssetsData.Success(assets)
        } catch (e: Exception) {
            AssetsData.Fail(e)
        }
    }
}