package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class MarketsDomainTest {
    @Test
    fun test_success() {
        val markets = listOf(
            MarketData(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
            MarketData(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
        )

        val resultMarkets = listOf(
            MarketDomain(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
            MarketDomain(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
        )

        val marketMapper = BaseMarketDomainToUiMapper()
        val domain = MarketsDomain.Success(markets, BaseMarketDataToDomainMapper())
        val expected = MarketsUi.Success(resultMarkets, marketMapper)
        val actual = domain.map(BaseMarketsDomainToUiMapper(TestResourceProvider(), marketMapper))
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = MarketsDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = MarketsUi.Fail(NO_CONNECTION_MESSAGE)
        val actual = domain.map(BaseMarketsDomainToUiMapper(
            TestResourceProvider(),
            BaseMarketDomainToUiMapper)
        )
        assertEquals(expected, actual)

        domain = MarketsDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = MarketsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(BaseMarketsDomainToUiMapper(
            TestResourceProvider(),
            BaseMarketDomainToUiMapper)
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}