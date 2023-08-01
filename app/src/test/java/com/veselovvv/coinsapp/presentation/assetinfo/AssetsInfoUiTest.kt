package com.veselovvv.coinsapp.presentation.assetinfo

import com.veselovvv.coinsapp.domain.assetinfo.AssetInfoDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class AssetsInfoUiTest {
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

        val mapper = BaseAssetInfoDomainToUiMapper()
        val ui = AssetsInfoUi.Success(assetInfo, mapper)
        val communication = TestAssetsInfoCommunication()
        ui.map(communication)

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

    @Test
    fun test_fail() {
        var ui = AssetsInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var communication = TestAssetsInfoCommunication()
        ui.map(communication)

        var expected = AssetInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = communication.getAssetInfo()
        assertEquals(expected, actual)

        ui = AssetsInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        communication = TestAssetsInfoCommunication()
        ui.map(communication)

        expected = AssetInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = communication.getAssetInfo()
        assertEquals(expected, actual)

        ui = AssetsInfoUi.Fail(GENERIC_ERROR_MESSAGE)
        communication = TestAssetsInfoCommunication()
        ui.map(communication)

        expected = AssetInfoUi.Fail(GENERIC_ERROR_MESSAGE)
        actual = communication.getAssetInfo()
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}