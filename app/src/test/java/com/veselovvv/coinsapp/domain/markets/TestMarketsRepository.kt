package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.data.markets.MarketData
import com.veselovvv.coinsapp.data.markets.MarketsData
import com.veselovvv.coinsapp.data.markets.MarketsRepository

class TestMarketsRepository(private val exception: Exception? = null) : MarketsRepository {
    private val markets = listOf(
        MarketData(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
        MarketData(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
    )

    private val foundMarkets = listOf(
        MarketData(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD")
    )

    override suspend fun fetchMarkets() =
        if (exception == null) MarketsData.Success(markets) else MarketsData.Fail(exception)

    override suspend fun searchMarkets(query: String) =
        if (exception == null) MarketsData.Success(foundMarkets) else MarketsData.Fail(exception)
}