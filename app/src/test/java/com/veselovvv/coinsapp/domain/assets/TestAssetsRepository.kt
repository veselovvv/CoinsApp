package com.veselovvv.coinsapp.domain.assets

class TestAssetsRepository(private val exception: Exception? = null) : AssetsRepository {
    private val assets = listOf(
        AssetData(rank = "1", symbol = "BTC", name = "Bitcoin"),
        AssetData(rank = "5", symbol = "USDC", name = "USD Coin")
    )

    private val foundAssets = listOf(AssetData(rank = "1", symbol = "BTC", name = "Bitcoin"))

    override suspend fun fetchAssets() =
        if (exception == null) AssetsData.Success(assets) else AssetsData.Fail(exception)

    override suspend fun searchAssets(query: String) =
        if (exception == null) AssetsData.Success(foundAssets) else AssetsData.Fail(exception)
}