package com.veselovvv.coinsapp.presentation.assets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.assets.AssetData
import com.veselovvv.coinsapp.domain.assets.AssetsDomain
import com.veselovvv.coinsapp.domain.assets.BaseAssetDataToDomainMapper
import com.veselovvv.coinsapp.domain.assets.FetchAssetsUseCase
import com.veselovvv.coinsapp.domain.assets.SearchAssetsUseCase
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class AssetsViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_assets_success() = runBlocking {
        val communication = TestAssetsCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = AssetsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchAssetsUseCase(success = true),
            TestSearchAssetsUseCase(),
            BaseAssetsDomainToUiMapper(TestResourceProvider(), BaseAssetDomainToUiMapper()),
            TestAssetCache()
        )

        viewModel.fetchAssets()

        val expected = listOf<AssetUi>(
            AssetUi.Base(rank = "1", symbol = "BTC", name = "Bitcoin"),
            AssetUi.Base(rank = "5", symbol = "USDC", name = "USD Coin")
        )

        val actual = communication.getAssets()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_assets_fail() = runBlocking {
        val communication = TestAssetsCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = AssetsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchAssetsUseCase(success = false),
            TestSearchAssetsUseCase(),
            BaseAssetsDomainToUiMapper(TestResourceProvider(), BaseAssetDomainToUiMapper()),
            TestAssetCache()
        )

        viewModel.fetchAssets()

        val expected = listOf(AssetUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getAssets()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_search_assets_success() = runBlocking {
        val communication = TestAssetsCommunication()
        val dispatchers = UnconfinedTestDispatcher()
        val fetchAssetsUseCase = TestFetchAssetsUseCase()
        val assetsMapper = BaseAssetsDomainToUiMapper(
            TestResourceProvider(),
            BaseAssetDomainToUiMapper()
        )
        val assetsCache = TestAssetCache()

        var viewModel = AssetsViewModel(
            communication,
            dispatchers,
            dispatchers,
            fetchAssetsUseCase,
            TestSearchAssetsUseCase(success = true, isListEmpty = false),
            assetsMapper,
            assetsCache
        )

        viewModel.searchAssets(query = "Bitcoin")

        var expected = listOf<AssetUi>(
            AssetUi.Base(rank = "1", symbol = "BTC", name = "Bitcoin")
        )

        var actual = communication.getAssets()
        assertEquals(expected, actual)

        viewModel = AssetsViewModel(
            communication,
            dispatchers,
            dispatchers,
            fetchAssetsUseCase,
            TestSearchAssetsUseCase(success = true, isListEmpty = true),
            assetsMapper,
            assetsCache
        )

        viewModel.searchAssets(query = "Element that does not exist")

        expected = listOf<AssetUi>(AssetUi.NoResults)
        actual = communication.getAssets()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_search_assets_fail() = runBlocking {
        val communication = TestAssetsCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = AssetsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchAssetsUseCase(success = false),
            TestSearchAssetsUseCase(success = false, isListEmpty = false),
            BaseAssetsDomainToUiMapper(TestResourceProvider(), BaseAssetDomainToUiMapper()),
            TestAssetCache()
        )

        viewModel.searchAssets(query = "Some query")

        val expected = listOf(AssetUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getAssets()
        assertEquals(expected, actual)
    }

    class TestFetchAssetsUseCase(private val success: Boolean = true) : FetchAssetsUseCase {
        override suspend fun execute() = if (success) AssetsDomain.Success(
            listOf(
                AssetData(rank = "1", symbol = "BTC", name = "Bitcoin"),
                AssetData(rank = "5", symbol = "USDC", name = "USD Coin")
            ),
            BaseAssetDataToDomainMapper()
        ) else AssetsDomain.Fail(ErrorType.GENERIC_ERROR)
    }

    class TestSearchAssetsUseCase(
        private val success: Boolean = true,
        private val isListEmpty: Boolean = false
    ) : SearchAssetsUseCase {
        override suspend fun execute(query: String) = if (success) {
            if (isListEmpty)
                AssetsDomain.Success(listOf(), BaseAssetDataToDomainMapper())
            else AssetsDomain.Success(
                listOf(AssetData(rank = "1", symbol = "BTC", name = "Bitcoin")),
                BaseAssetDataToDomainMapper()
            )
        } else AssetsDomain.Fail(ErrorType.GENERIC_ERROR)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}