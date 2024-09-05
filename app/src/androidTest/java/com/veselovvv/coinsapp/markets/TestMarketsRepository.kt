package com.veselovvv.coinsapp.markets

import com.veselovvv.coinsapp.data.markets.MarketData
import com.veselovvv.coinsapp.data.markets.MarketsData
import com.veselovvv.coinsapp.data.markets.MarketsRepository
import java.net.UnknownHostException

class TestMarketsRepository : MarketsRepository {
    private var isSuccess = false

    private val markets = listOf(
        MarketData(exchangeId = "alterdice", baseSymbol = "BTC", quoteSymbol = "USDT"),
        MarketData(exchangeId = "alterdice", baseSymbol = "LTC", quoteSymbol = "USDT"),
        MarketData(exchangeId = "alterdice", baseSymbol = "FTM", quoteSymbol = "USDT")
    )

    private val foundMarkets = listOf(
        MarketData(exchangeId = "alterdice", baseSymbol = "BTC", quoteSymbol = "USDT"),
        MarketData(exchangeId = "alterdice", baseSymbol = "LTC", quoteSymbol = "USDT"),
        MarketData(exchangeId = "alterdice", baseSymbol = "FTM", quoteSymbol = "USDT")
    )

    override suspend fun fetchMarkets(): MarketsData {
        isSuccess = !isSuccess

        return if (isSuccess)
            MarketsData.Success(markets)
        else
            MarketsData.Fail(UnknownHostException())
    }

    override suspend fun searchMarkets(query: String) =
        MarketsData.Success(
            when {
                query == "" -> markets
                query.startsWith("alter") -> foundMarkets
                else -> listOf()
            }
        )
}