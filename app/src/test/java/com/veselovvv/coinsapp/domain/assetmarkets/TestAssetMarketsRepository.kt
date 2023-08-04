package com.veselovvv.coinsapp.domain.assetmarkets

class TestAssetMarketsRepository(
    private val exception: Exception? = null
) : AssetMarketsRepository {
    private val assetsMarkets = listOf(
        AssetMarketsData(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
        AssetMarketsData(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
    )

    private val foundAssetsMarkets = listOf(
        AssetMarketsData(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether")
    )

    override suspend fun fetchAssetMarkets(assetId: String) = if (exception == null)
        AssetsMarketsData.Success(assetsMarkets) else AssetsMarketsData.Fail(exception)

    override suspend fun searchAssetMarkets(assetId: String, query: String) = if (exception == null)
        AssetsMarketsData.Success(foundAssetsMarkets) else AssetsMarketsData.Fail(exception)
}