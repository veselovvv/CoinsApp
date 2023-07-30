package com.veselovvv.coinsapp.data.assets

import com.veselovvv.coinsapp.data.TestException
import com.veselovvv.coinsapp.data.assets.cloud.AssetCloud
import com.veselovvv.coinsapp.data.assets.cloud.AssetsCloudDataSource
import com.veselovvv.coinsapp.data.assets.cloud.AssetsCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseAssetsRepositoryTest {
    @Test
    fun test_fetch_assets_success() = runBlocking {
        val repository = AssetsRepository.Base(
            TestAssetsCloudDataSource(true),
            AssetsCloudMapper.Base(ToAssetMapper.Base())
        )

        val expected = AssetsData.Success(
            listOf(
                AssetData(id = "bitcoin", rank = "1", symbol = "BTC", name = "Bitcoin"),
                AssetData(id = "usd-coin", rank = "5", symbol = "USDC", name = "USD Coin")
            )
        )

        val actual = repository.fetchAssets()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_assets_fail() = runBlocking {
        val repository = AssetsRepository.Base(
            TestAssetsCloudDataSource(false),
            AssetsCloudMapper.Base(ToAssetMapper.Base())
        )

        val expected = AssetsData.Fail(TestException(""))
        val actual = repository.fetchAssets()
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_assets_success() = runBlocking {
        val repository = AssetsRepository.Base(
            TestAssetsCloudDataSource(true),
            AssetsCloudMapper.Base(ToAssetMapper.Base())
        )

        val expected = AssetsData.Success(
            listOf(
                AssetData(id = "bitcoin", rank = "1", symbol = "BTC", name = "Bitcoin")
            )
        )

        val actual = repository.searchAssets(query = "Bitcoin")
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_assets_fail() = runBlocking {
        val repository = AssetsRepository.Base(
            TestAssetsCloudDataSource(true),
            AssetsCloudMapper.Base(ToAssetMapper.Base())
        )

        val expected = AssetsData.Success(listOf())
        val actual = repository.searchAssets(query = "Az")
        assertEquals(expected, actual)
    }

    class TestAssetsCloudDataSource(private val success: Boolean) : AssetsCloudDataSource {
        override suspend fun fetchAssets() = if (success) listOf(
            AssetCloud(id = "bitcoin", rank = "1", symbol = "BTC", name = "Bitcoin"),
            AssetCloud(id = "usd-coin", rank = "5", symbol = "USDC", name = "USD Coin")
        ) else throw TestException("")
    }
}