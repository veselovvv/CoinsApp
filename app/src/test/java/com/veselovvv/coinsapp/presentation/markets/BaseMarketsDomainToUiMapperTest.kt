package com.veselovvv.coinsapp.presentation.markets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.domain.markets.MarketDomain
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseMarketsDomainToUiMapperTest {
    private val marketMapper = BaseMarketDomainToUiMapper()
    private val mapper = BaseMarketsDomainToUiMapper(TestResourceProvider(), marketMapper)

    @Test
    fun test_success() {
        val markets = listOf(
            MarketDomain(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
            MarketDomain(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
        )

        val expected = MarketsUi.Success(markets, marketMapper)
        val actual = mapper.map(markets)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = MarketsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = MarketsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}