package com.veselovvv.coinsapp.assets

import com.veselovvv.coinsapp.data.assets.AssetData
import com.veselovvv.coinsapp.data.assets.AssetsData
import com.veselovvv.coinsapp.data.assets.AssetsRepository
import java.net.UnknownHostException

class TestAssetsRepository : AssetsRepository {
    private var isSuccess = false

    private val assets = listOf(
        AssetData(id = "bitcoin", rank = "1", symbol = "BTC", name = "Bitcoin"),
        AssetData(id = "eth", rank = "2", symbol = "ETH", name = "Ethereum"),
        AssetData(id = "usdt", rank = "3", symbol = "USDT", name = "Tether")
    )

    private val foundAssets = listOf(AssetData(id = "bitcoin", rank = "1", symbol = "BTC", name = "Bitcoin"))

    override suspend fun fetchAssets(): AssetsData {
        isSuccess = !isSuccess

        return if (isSuccess)
            AssetsData.Success(assets)
        else
            AssetsData.Fail(UnknownHostException())
    }

    override suspend fun searchAssets(query: String) =
        AssetsData.Success(
            when {
                query == "" -> assets
                query.startsWith("Bit") -> foundAssets
                else -> listOf()
            }
        )
}