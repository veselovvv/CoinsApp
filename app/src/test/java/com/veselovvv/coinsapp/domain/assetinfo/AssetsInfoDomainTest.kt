package com.veselovvv.coinsapp.domain.assetinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.assetinfo.AssetInfoData
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import com.veselovvv.coinsapp.presentation.assetinfo.AssetsInfoUi
import com.veselovvv.coinsapp.presentation.assetinfo.BaseAssetInfoDomainToUiMapper
import com.veselovvv.coinsapp.presentation.assetinfo.BaseAssetsInfoDomainToUiMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class AssetsInfoDomainTest {
    @Test
    fun test_success() {
        val assetInfo = AssetInfoData(
            supply = "17193925.0000000000000000",
            maxSupply = "21000000.0000000000000000",
            marketCapUsd = "119179791817.6740161068269075",
            volumeUsd24Hr = "2928356777.6066665425687196",
            priceUsd = "6931.5058555666618359",
            changePercent24Hr = "-0.8101417214350335",
            vwap24Hr = "7175.0663247679233209"
        )

        val domain = AssetsInfoDomain.Success(assetInfo, BaseAssetInfoDataToDomainMapper())

        val resultAssetInfo = AssetInfoDomain(
            supply = "17193925.0000000000000000",
            maxSupply = "21000000.0000000000000000",
            marketCapUsd = "119179791817.6740161068269075",
            volumeUsd24Hr = "2928356777.6066665425687196",
            priceUsd = "6931.5058555666618359",
            changePercent24Hr = "-0.8101417214350335",
            vwap24Hr = "7175.0663247679233209"
        )

        val assetInfoMapper = BaseAssetInfoDomainToUiMapper()
        val expected = AssetsInfoUi.Success(resultAssetInfo, assetInfoMapper)
        val actual = domain.map(
            BaseAssetsInfoDomainToUiMapper(TestResourceProvider(), assetInfoMapper)
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = AssetsInfoDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = AssetsInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(
            BaseAssetsInfoDomainToUiMapper(TestResourceProvider(), BaseAssetInfoDomainToUiMapper())
        )
        assertEquals(expected, actual)

        domain = AssetsInfoDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = AssetsInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(
            BaseAssetsInfoDomainToUiMapper(TestResourceProvider(), BaseAssetInfoDomainToUiMapper())
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}