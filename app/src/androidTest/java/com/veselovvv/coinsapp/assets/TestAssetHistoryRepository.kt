package com.veselovvv.coinsapp.assets

import com.veselovvv.coinsapp.data.assethistory.AssetHistoryData
import com.veselovvv.coinsapp.data.assethistory.AssetHistoryRepository
import com.veselovvv.coinsapp.data.assethistory.AssetsHistoryData
import java.net.UnknownHostException

class TestAssetHistoryRepository: AssetHistoryRepository {
    private var isSuccess = false

    private val assetsHistory = listOf(
        AssetHistoryData(priceUsd = "6379.3997635993342453", time = "1530403200000"),
        AssetHistoryData(priceUsd = "5249.2897635663782442", time = "1340203100000"),
        AssetHistoryData(priceUsd = "6379.6558636794542412", time = "1420703400000")
    )

    override suspend fun fetchAssetHistory(assetId: String): AssetsHistoryData {
        isSuccess = !isSuccess

        return if (isSuccess) AssetsHistoryData.Success(assetsHistory)
        else AssetsHistoryData.Fail(UnknownHostException())
    }
}