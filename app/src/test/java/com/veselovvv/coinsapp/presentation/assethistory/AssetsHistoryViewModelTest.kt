package com.veselovvv.coinsapp.presentation.assethistory

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class AssetsHistoryViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_asset_history_success() = runBlocking {
        val communication = TestAssetsHistoryCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = AssetsHistoryViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchAssetHistoryUseCase(success = true),
            BaseAssetsHistoryDomainToUiMapper(TestResourceProvider(), BaseAssetHistoryDomainToUiMapper())
        )

        viewModel.fetchAssetHistory(assetId = "bitcoin")

        val expected = listOf<AssetHistoryUi>(
            AssetHistoryUi.Base(priceUsd = "6379.3997635993342453", time = "1530403200000"),
            AssetHistoryUi.Base(priceUsd = "6466.3135622762295280", time = "1530489600000")
        )

        val actual = communication.getAssetHistory()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_asset_history_fail() = runBlocking {
        val communication = TestAssetsHistoryCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = AssetsHistoryViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchAssetHistoryUseCase(success = false),
            BaseAssetsHistoryDomainToUiMapper(TestResourceProvider(), BaseAssetHistoryDomainToUiMapper())
        )

        viewModel.fetchAssetHistory(assetId = "bitcoin")

        val expected = listOf<AssetHistoryUi>(AssetHistoryUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getAssetHistory()
        assertEquals(expected, actual)
    }

    class TestFetchAssetHistoryUseCase(private val success: Boolean) : FetchAssetHistoryUseCase {
        override suspend fun execute(assetId: String) = if (success) AssetsHistoryDomain.Success(
            listOf(
                AssetHistoryData(priceUsd = "6379.3997635993342453", time = "1530403200000"),
                AssetHistoryData(priceUsd = "6466.3135622762295280", time = "1530489600000")
            ),
            BaseAssetHistoryDataToDomainMapper()
        ) else AssetsHistoryDomain.Fail(ErrorType.GENERIC_ERROR)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}