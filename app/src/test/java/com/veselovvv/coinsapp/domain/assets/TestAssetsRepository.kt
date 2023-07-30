package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.data.assets.AssetData
import com.veselovvv.coinsapp.data.assets.AssetsData
import com.veselovvv.coinsapp.data.assets.AssetsRepository

class TestAssetsRepository(private val exception: Exception? = null) : AssetsRepository {
    private val assets = listOf(
        AssetData(id = "bitcoin", rank = "1", symbol = "BTC", name = "Bitcoin"),
        AssetData(id = "usd-coin", rank = "5", symbol = "USDC", name = "USD Coin")
    )

    private val foundAssets = listOf(AssetData(id = "bitcoin", rank = "1", symbol = "BTC", name = "Bitcoin"))

    override suspend fun fetchAssets() =
        if (exception == null) AssetsData.Success(assets) else AssetsData.Fail(exception)

    override suspend fun searchAssets(query: String) =
        if (exception == null) AssetsData.Success(foundAssets) else AssetsData.Fail(exception)
}