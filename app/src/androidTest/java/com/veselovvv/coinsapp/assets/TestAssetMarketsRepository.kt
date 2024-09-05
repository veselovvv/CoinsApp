package com.veselovvv.coinsapp.assets

import com.veselovvv.coinsapp.data.assetmarkets.AssetMarketsData
import com.veselovvv.coinsapp.data.assetmarkets.AssetMarketsRepository
import com.veselovvv.coinsapp.data.assetmarkets.AssetsMarketsData
import java.net.UnknownHostException

class TestAssetMarketsRepository(
) : AssetMarketsRepository {
    private var isSuccess = false

    private val assetsMarkets = listOf(
        AssetMarketsData(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
        AssetMarketsData(exchangeId = "WhiteBIT", baseId = "bitcoin", quoteId = "tether"),
        AssetMarketsData(exchangeId = "LBank", baseId = "bitcoin", quoteId = "tether")
    )

    private val foundAssetsMarkets = listOf(
        AssetMarketsData(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether")
    )

    override suspend fun fetchAssetMarkets(assetId: String): AssetsMarketsData {
        isSuccess = !isSuccess

        return if (isSuccess)
            AssetsMarketsData.Success(assetsMarkets)
        else
            AssetsMarketsData.Fail(UnknownHostException())
    }

    override suspend fun searchAssetMarkets(assetId: String, query: String) =
        AssetsMarketsData.Success(
            when {
                query == "" -> assetsMarkets
                query.startsWith("Bin") -> foundAssetsMarkets
                else -> listOf()
            }
        )
}