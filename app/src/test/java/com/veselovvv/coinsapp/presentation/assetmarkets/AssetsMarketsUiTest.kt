package com.veselovvv.coinsapp.presentation.assetmarkets

import com.veselovvv.coinsapp.domain.assetmarkets.AssetMarketsDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class AssetsMarketsUiTest {
    @Test
    fun test_success() {
        val assetsMarkets = listOf(
            AssetMarketsDomain(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
            AssetMarketsDomain(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
        )

        var ui = AssetsMarketsUi.Success(assetsMarkets, BaseAssetMarketsDomainToUiMapper())
        val communication = TestAssetsMarketsCommunication()
        ui.map(communication)

        var expected = listOf<AssetMarketsUi>(
            AssetMarketsUi.Base(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
            AssetMarketsUi.Base(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
        )

        var actual = communication.getAssetsMarkets()
        assertEquals(expected, actual)

        ui = AssetsMarketsUi.Success(listOf(), BaseAssetMarketsDomainToUiMapper())
        ui.map(communication)

        expected = listOf<AssetMarketsUi>(AssetMarketsUi.NoResults)
        actual = communication.getAssetsMarkets()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val ui = AssetsMarketsUi.Fail(GENERIC_ERROR_MESSAGE)
        val communication = TestAssetsMarketsCommunication()
        ui.map(communication)

        val expected = listOf<AssetMarketsUi>(AssetMarketsUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getAssetsMarkets()
        assertEquals(expected, actual)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}