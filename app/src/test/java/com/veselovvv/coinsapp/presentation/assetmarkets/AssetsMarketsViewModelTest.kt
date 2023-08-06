package com.veselovvv.coinsapp.presentation.assetmarkets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class AssetsMarketsViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_asset_markets_success() = runBlocking {
        val communication = TestAssetsMarketsCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = AssetsMarketsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchAssetMarketsUseCase(success = true),
            TestSearchAssetMarketsUseCase(),
            BaseAssetsMarketsDomainToUiMapper(TestResourceProvider(), BaseAssetMarketsDomainToUiMapper()),
            TestAssetMarketsCache()
        )

        viewModel.fetchAssetMarkets(assetId = "bitcoin")

        val expected = listOf<AssetMarketsUi>(
            AssetMarketsUi.Base(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
            AssetMarketsUi.Base(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
        )

        val actual = communication.getAssetsMarkets()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_asset_markets_fail() = runBlocking {
        val communication = TestAssetsMarketsCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = AssetsMarketsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchAssetMarketsUseCase(success = false),
            TestSearchAssetMarketsUseCase(),
            BaseAssetsMarketsDomainToUiMapper(TestResourceProvider(), BaseAssetMarketsDomainToUiMapper()),
            TestAssetMarketsCache()
        )

        viewModel.fetchAssetMarkets(assetId = "bitcoin")

        val expected = listOf<AssetMarketsUi>(AssetMarketsUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getAssetsMarkets()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_search_asset_markets_success() = runBlocking {
        val communication = TestAssetsMarketsCommunication()
        val dispatchers = UnconfinedTestDispatcher()
        val fetchAssetMarketsUseCase = TestFetchAssetMarketsUseCase()
        val assetsMarketsMapper = BaseAssetsMarketsDomainToUiMapper(
            TestResourceProvider(),
            BaseAssetMarketsDomainToUiMapper()
        )
        val assetMarketsCache = TestAssetMarketsCache()

        var viewModel = AssetsMarketsViewModel(
            communication,
            dispatchers,
            dispatchers,
            fetchAssetMarketsUseCase,
            TestSearchAssetMarketsUseCase(success = true, isListEmpty = false),
            assetsMarketsMapper,
            assetMarketsCache
        )

        viewModel.searchAssetMarkets(assetId = "bitcoin", query = "Bin")

        var expected = listOf<AssetMarketsUi>(
            AssetMarketsUi.Base(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether")
        )

        var actual = communication.getAssetsMarkets()
        assertEquals(expected, actual)

        viewModel = AssetsMarketsViewModel(
            communication,
            dispatchers,
            dispatchers,
            fetchAssetMarketsUseCase,
            TestSearchAssetMarketsUseCase(success = true, isListEmpty = true),
            assetsMarketsMapper,
            assetMarketsCache
        )

        viewModel.searchAssetMarkets(assetId = "bitcoin", query = "Binzxc")

        expected = listOf<AssetMarketsUi>(AssetMarketsUi.NoResults)
        actual = communication.getAssetsMarkets()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_search_asset_markets_fail() = runBlocking {
        val communication = TestAssetsMarketsCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = AssetsMarketsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchAssetMarketsUseCase(success = false),
            TestSearchAssetMarketsUseCase(success = false, isListEmpty = false),
            BaseAssetsMarketsDomainToUiMapper(TestResourceProvider(), BaseAssetMarketsDomainToUiMapper()),
            TestAssetMarketsCache()
        )

        viewModel.searchAssetMarkets(assetId = "bitcoin", query = "Bin")

        val expected = listOf<AssetMarketsUi>(AssetMarketsUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getAssetsMarkets()
        assertEquals(expected, actual)
    }

    class TestFetchAssetMarketsUseCase(private val success: Boolean = true) : FetchAssetMarketsUseCase {
        override suspend fun execute(assetId: String) = if (success) AssetsMarketsDomain.Success(
            listOf(
                AssetMarketsData(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
                AssetMarketsData(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
            ),
            BaseAssetMarketsDataToDomainMapper()
        ) else AssetsMarketsDomain.Fail(ErrorType.GENERIC_ERROR)
    }

    class TestSearchAssetMarketsUseCase(
        private val success: Boolean = true,
        private val isListEmpty: Boolean = false
    ) : SearchAssetMarketsUseCase {
        override suspend fun execute(assetId: String, query: String) = if (success) {
            if (isListEmpty)
                AssetsMarketsDomain.Success(listOf(), BaseAssetMarketsDataToDomainMapper())
            else AssetsMarketsDomain.Success(
                listOf(AssetMarketsData(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether")),
                BaseAssetMarketsDataToDomainMapper()
            )
        } else AssetsMarketsDomain.Fail(ErrorType.GENERIC_ERROR)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}