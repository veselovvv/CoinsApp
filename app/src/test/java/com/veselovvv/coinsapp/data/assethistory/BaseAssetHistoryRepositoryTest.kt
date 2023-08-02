package com.veselovvv.coinsapp.data.assethistory

import com.veselovvv.coinsapp.data.TestException
import com.veselovvv.coinsapp.data.assethistory.cloud.AssetHistoryCloud
import com.veselovvv.coinsapp.data.assethistory.cloud.AssetHistoryCloudDataSource
import com.veselovvv.coinsapp.data.assethistory.cloud.AssetHistoryCloudMapper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BaseAssetHistoryRepositoryTest {
    @Test
    fun test_fetch_asset_history_success() = runBlocking {
        val repository = AssetHistoryRepository.Base(
            TestAssetHistoryCloudDataSource(true),
            AssetHistoryCloudMapper.Base(ToAssetHistoryMapper.Base())
        )

        val expected = AssetsHistoryData.Success(
            listOf(
                AssetHistoryData(priceUsd = "6379.3997635993342453", time = "1530403200000"),
                AssetHistoryData(priceUsd = "6466.3135622762295280", time = "1530489600000")
            )
        )

        val actual = repository.fetchAssetHistory(assetId = "bitcoin")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_asset_history_fail() = runBlocking {
        val repository = AssetHistoryRepository.Base(
            TestAssetHistoryCloudDataSource(false),
            AssetHistoryCloudMapper.Base(ToAssetHistoryMapper.Base())
        )

        val expected = AssetsHistoryData.Fail(TestException(""))
        val actual = repository.fetchAssetHistory(assetId = "bitcoin")
        assertEquals(expected, actual)
    }

    class TestAssetHistoryCloudDataSource(
        private val success: Boolean
    ) : AssetHistoryCloudDataSource {
        override suspend fun fetchAssetHistory(assetId: String) = if (success) listOf(
            AssetHistoryCloud(priceUsd = "6379.3997635993342453", time = 1530403200000L),
            AssetHistoryCloud(priceUsd = "6466.3135622762295280", time = 1530489600000L)
        ) else throw TestException("")
    }
}