package com.veselovvv.coinsapp.presentation.assetinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.TestException
import com.veselovvv.coinsapp.data.assetinfo.AssetInfoData
import com.veselovvv.coinsapp.domain.assetinfo.AssetsInfoDomain
import com.veselovvv.coinsapp.domain.assetinfo.BaseAssetInfoDataToDomainMapper
import com.veselovvv.coinsapp.domain.assetinfo.FetchAssetInfoUseCase
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import com.veselovvv.coinsapp.presentation.assets.TestAssetCache
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class AssetsInfoViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_asset_info_success() = runBlocking {
        val communication = TestAssetsInfoCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = AssetsInfoViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchAssetInfoUseCase(),
            BaseAssetsInfoDomainToUiMapper(TestResourceProvider(), BaseAssetInfoDomainToUiMapper()),
            TestAssetCache()
        )
        viewModel.fetchAssetInfo(id = "bitcoin")

        val expected = AssetInfoUi.Base(
            supply = "17193925.0000000000000000",
            maxSupply = "21000000.0000000000000000",
            marketCapUsd = "119179791817.6740161068269075",
            volumeUsd24Hr = "2928356777.6066665425687196",
            priceUsd = "6931.5058555666618359",
            changePercent24Hr = "-0.8101417214350335",
            vwap24Hr = "7175.0663247679233209"
        )
        val actual = communication.getAssetInfo()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_asset_info_fail() = runBlocking {
        var communication = TestAssetsInfoCommunication()
        var dispatchers = UnconfinedTestDispatcher()

        var viewModel = AssetsInfoViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchAssetInfoUseCase(ErrorType.NO_CONNECTION),
            BaseAssetsInfoDomainToUiMapper(TestResourceProvider(), BaseAssetInfoDomainToUiMapper()),
            TestAssetCache()
        )
        viewModel.fetchAssetInfo(id = "bitcoin")

        var expected = AssetInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = communication.getAssetInfo()
        assertEquals(expected, actual)

        communication = TestAssetsInfoCommunication()
        dispatchers = UnconfinedTestDispatcher()

        viewModel = AssetsInfoViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchAssetInfoUseCase(ErrorType.SERVICE_UNAVAILABLE),
            BaseAssetsInfoDomainToUiMapper(TestResourceProvider(), BaseAssetInfoDomainToUiMapper()),
            TestAssetCache()
        )
        viewModel.fetchAssetInfo(id = "bitcoin")

        expected = AssetInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = communication.getAssetInfo()
        assertEquals(expected, actual)

        communication = TestAssetsInfoCommunication()
        dispatchers = UnconfinedTestDispatcher()

        viewModel = AssetsInfoViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchAssetInfoUseCase(ErrorType.GENERIC_ERROR),
            BaseAssetsInfoDomainToUiMapper(TestResourceProvider(), BaseAssetInfoDomainToUiMapper()),
            TestAssetCache()
        )
        viewModel.fetchAssetInfo(id = "bitcoin")

        expected = AssetInfoUi.Fail(GENERIC_ERROR_MESSAGE)
        actual = communication.getAssetInfo()
        assertEquals(expected, actual)
    }

    class TestFetchAssetInfoUseCase(private val error: ErrorType? = null) : FetchAssetInfoUseCase {
        override suspend fun execute(id: String) = if (error == null) AssetsInfoDomain.Success(
            AssetInfoData(
                supply = "17193925.0000000000000000",
                maxSupply = "21000000.0000000000000000",
                marketCapUsd = "119179791817.6740161068269075",
                volumeUsd24Hr = "2928356777.6066665425687196",
                priceUsd = "6931.5058555666618359",
                changePercent24Hr = "-0.8101417214350335",
                vwap24Hr = "7175.0663247679233209"
            ),
            BaseAssetInfoDataToDomainMapper()
        ) else AssetsInfoDomain.Fail(error)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}