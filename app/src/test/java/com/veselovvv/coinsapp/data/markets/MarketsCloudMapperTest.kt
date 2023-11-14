package com.veselovvv.coinsapp.data.markets

import com.veselovvv.coinsapp.data.markets.cloud.MarketCloud
import com.veselovvv.coinsapp.data.markets.cloud.MarketsCloudMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class MarketsCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = MarketsCloudMapper.Base(ToMarketMapper.Base())

        val markets = listOf(
            MarketCloud(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
            MarketCloud(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
        )

        val expected = listOf(
            MarketData(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
            MarketData(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
        )

        val actual = mapper.map(markets)
        assertEquals(expected, actual)
    }
}