package com.veselovvv.coinsapp.presentation.markets

import org.junit.Assert.assertEquals
import org.junit.Test

class MarketsUiTest {
    @Test
    fun test_success() {
        val markets = listOf(
            MarketDomain(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
            MarketDomain(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
        )

        var ui = MarketsUi.Success(markets, BaseMarketDomainToUiMapper())
        val communication = TestMarketsCommunication()
        ui.map(communication)

        var expected = listOf<MarketUi>(
            MarketUi.Base(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
            MarketUi.Base(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
        )

        var actual = communication.getMarkets()
        assertEquals(expected, actual)

        ui = MarketsUi.Success(listOf(), BaseMarketDomainToUiMapper())
        ui.map(communication)

        expected = listOf<MarketUi>(MarketUi.NoResults)
        actual = communication.getMarkets()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val ui = MarketsUi.Fail(GENERIC_ERROR_MESSAGE)
        val communication = TestMarketsCommunication()
        ui.map(communication)

        val expected = listOf<MarketUi>(MarketUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getMarkets()
        assertEquals(expected, actual)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}