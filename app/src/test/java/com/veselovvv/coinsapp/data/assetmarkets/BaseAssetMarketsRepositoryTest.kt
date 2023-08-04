package com.veselovvv.coinsapp.data.assetmarkets

import com.veselovvv.coinsapp.data.TestException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseAssetMarketsRepositoryTest {
    @Test
    fun test_fetch_asset_markets_success() = runBlocking {
        val repository = AssetMarketsRepository.Base(
            TestAssetMarketsCloudDataSource(true),
            AssetMarketsCloudMapper.Base(ToAssetMarketsMapper.Base())
        )

        val expected = AssetsMarketsData.Success(
            listOf(
                AssetMarketsData(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
                AssetMarketsData(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
            )
        )

        val actual = repository.fetchAssetMarkets(assetId = "bitcoin")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_asset_markets_fail() = runBlocking {
        val repository = AssetMarketsRepository.Base(
            TestAssetMarketsCloudDataSource(false),
            AssetMarketsCloudMapper.Base(ToAssetMarketsMapper.Base())
        )

        val expected = AssetsMarketsData.Fail(TestException(""))
        val actual = repository.fetchAssetMarkets(assetId = "bitcoin")
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_asset_markets_success() = runBlocking {
        val repository = AssetMarketsRepository.Base(
            TestAssetMarketsCloudDataSource(true),
            AssetMarketsCloudMapper.Base(ToAssetMarketsMapper.Base())
        )

        val expected = AssetsMarketsData.Success(
            listOf(
                AssetMarketsData(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether")
            )
        )

        val actual = repository.searchAssetMarkets(query = "Bin")
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_asset_markets_fail() = runBlocking {
        val repository = AssetMarketsRepository.Base(
            TestAssetMarketsCloudDataSource(true),
            AssetMarketsCloudMapper.Base(ToAssetMarketsMapper.Base())
        )

        val expected = AssetsMarketsData.Success(listOf())
        val actual = repository.searchAssetMarkets(query = "Binzcz")
        assertEquals(expected, actual)
    }

    class TestAssetMarketsCloudDataSource(private val success: Boolean) : AssetMarketsCloudDataSource {
        override suspend fun fetchAssetMarkets(assetId: String) = if (success) listOf(
            AssetMarketsCloud(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
            AssetMarketsCloud(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
        ) else throw TestException("")
    }
}