package com.veselovvv.coinsapp.data.markets

import com.veselovvv.coinsapp.data.TestException
import com.veselovvv.coinsapp.data.markets.cloud.MarketCloud
import com.veselovvv.coinsapp.data.markets.cloud.MarketsCloudDataSource
import com.veselovvv.coinsapp.data.markets.cloud.MarketsCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseMarketsRepositoryTest {
    @Test
    fun test_fetch_markets_success() = runBlocking {
        val repository = MarketsRepository.Base(
            TestMarketsCloudDataSource(true),
            MarketsCloudMapper.Base(ToMarketMapper.Base())
        )

        val expected = MarketsData.Success(
            listOf(
                MarketData(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
                MarketData(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
            )
        )

        val actual = repository.fetchMarkets()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_markets_fail() = runBlocking {
        val repository = MarketsRepository.Base(
            TestMarketsCloudDataSource(false),
            MarketsCloudMapper.Base(ToMarketMapper.Base())
        )

        val expected = MarketsData.Fail(TestException(""))
        val actual = repository.fetchMarkets()
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_markets_success() = runBlocking {
        val repository = MarketsRepository.Base(
            TestMarketsCloudDataSource(true),
            MarketsCloudMapper.Base(ToMarketMapper.Base())
        )

        val expected = MarketsData.Success(
            listOf(
                MarketData(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD")
            )
        )

        val actual = repository.searchMarkets(query = "bitstamp")
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_markets_fail() = runBlocking {
        val repository = MarketsRepository.Base(
            TestMarketsCloudDataSource(true),
            MarketsCloudMapper.Base(ToMarketMapper.Base())
        )

        val expected = MarketsData.Success(listOf())
        val actual = repository.searchMarkets(query = "Az")
        assertEquals(expected, actual)
    }

    class TestMarketsCloudDataSource(private val success: Boolean) : MarketsCloudDataSource {
        override suspend fun fetchMarkets() = if (success) listOf(
            MarketCloud(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
            MarketCloud(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
        ) else throw TestException("")
    }
}