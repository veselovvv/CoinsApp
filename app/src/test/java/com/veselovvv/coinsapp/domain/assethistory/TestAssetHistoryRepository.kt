package com.veselovvv.coinsapp.domain.assethistory

class TestAssetHistoryRepository(
    private val exception: Exception? = null
) : AssetHistoryRepository {
    private val assetsHistory = listOf(
        AssetHistoryData(priceUsd = "6379.3997635993342453", time = "1530403200000"),
        AssetHistoryData(priceUsd = "6466.3135622762295280", time = "1530489600000")
    )

    override suspend fun fetchAssetHistory(assetId: String) =
        if (exception == null) AssetsHistoryData.Success(assetsHistory)
        else AssetsHistoryData.Fail(exception)
}