package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.markets.MarketData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseMarketsDataToDomainMapperTest {
    private val marketMapper = BaseMarketDataToDomainMapper()
    private val mapper = BaseMarketsDataToDomainMapper(marketMapper)

    @Test
    fun test_success() {
        val markets = listOf(
            MarketData(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
            MarketData(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
        )

        val expected = MarketsDomain.Success(markets, marketMapper)
        val actual = mapper.map(markets)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = MarketsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = MarketsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}