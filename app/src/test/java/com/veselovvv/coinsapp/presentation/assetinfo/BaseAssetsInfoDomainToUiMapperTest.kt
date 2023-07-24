package com.veselovvv.coinsapp.presentation.assetinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseAssetsInfoDomainToUiMapperTest {
    private val assetInfoMapper = BaseAssetInfoDomainToUiMapper()
    private val mapper = BaseAssetsInfoDomainToUiMapper(TestResourceProvider(), assetInfoMapper)

    @Test
    fun test_success() {
        val assetInfo = AssetInfoDomain(
            supply = "17193925.0000000000000000",
            maxSupply = "21000000.0000000000000000",
            marketCapUsd = "119179791817.6740161068269075",
            volumeUsd24Hr = "2928356777.6066665425687196",
            priceUsd = "6931.5058555666618359",
            changePercent24Hr = "-0.8101417214350335",
            vwap24Hr = "7175.0663247679233209"
        )
        val expected = AssetsInfoUi.Success(assetInfo, assetInfoMapper)
        val actual = mapper.map(assetInfo)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = AssetsInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = AssetsInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}