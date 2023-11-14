package com.veselovvv.coinsapp.presentation.markets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.markets.MarketData
import com.veselovvv.coinsapp.domain.markets.BaseMarketDataToDomainMapper
import com.veselovvv.coinsapp.domain.markets.FetchMarketsUseCase
import com.veselovvv.coinsapp.domain.markets.MarketsDomain
import com.veselovvv.coinsapp.domain.markets.SearchMarketsUseCase
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class MarketsViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_markets_success() = runBlocking {
        val communication = TestMarketsCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = MarketsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchMarketsUseCase(success = true),
            TestSearchMarketsUseCase(),
            BaseMarketsDomainToUiMapper(TestResourceProvider(), BaseMarketDomainToUiMapper())
        )

        viewModel.fetchMarkets()

        val expected = listOf<MarketUi>(
            MarketUi.Base(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
            MarketUi.Base(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
        )

        val actual = communication.getMarkets()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_markets_fail() = runBlocking {
        val communication = TestMarketsCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = MarketsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchMarketsUseCase(success = false),
            TestSearchMarketsUseCase(),
            BaseMarketsDomainToUiMapper(TestResourceProvider(), BaseMarketDomainToUiMapper())
        )

        viewModel.fetchMarkets()

        val expected = listOf(MarketUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getMarkets()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_search_markets_success() = runBlocking {
        val communication = TestMarketsCommunication()
        val dispatchers = UnconfinedTestDispatcher()
        val fetchMarketsUseCase = TestFetchMarketsUseCase()
        val marketsMapper = BaseMarketsDomainToUiMapper(
            TestResourceProvider(),
            BaseMarketDomainToUiMapper()
        )

        var viewModel = MarketsViewModel(
            communication,
            dispatchers,
            dispatchers,
            fetchMarketsUseCase,
            TestSearchMarketsUseCase(success = true, isListEmpty = false),
            marketsMapper
        )

        viewModel.searchMarkets(query = "bitstamp")

        var expected = listOf<MarketUi>(
            MarketUi.Base(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD")
        )
        var actual = communication.getMarkets()
        assertEquals(expected, actual)

        viewModel = MarketsViewModel(
            communication,
            dispatchers,
            dispatchers,
            fetchMarketsUseCase,
            TestSearchMarketsUseCase(success = true, isListEmpty = true),
            marketsMapper
        )

        viewModel.searchMarkets(query = "Element that does not exist")

        expected = listOf<MarketUi>(MarketUi.NoResults)
        actual = communication.getMarkets()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_search_markets_fail() = runBlocking {
        val communication = TestMarketsCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = MarketsViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchMarketsUseCase(success = false),
            TestSearchMarketsUseCase(success = false, isListEmpty = false),
            BaseMarketsDomainToUiMapper(TestResourceProvider(), BaseMarketDomainToUiMapper())
        )

        viewModel.searchMarkets(query = "Some query")

        val expected = listOf(MarketUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getMarkets()
        assertEquals(expected, actual)
    }

    class TestFetchMarketsUseCase(private val success: Boolean = true) : FetchMarketsUseCase {
        override suspend fun execute() = if (success) MarketsDomain.Success(
            listOf(
                MarketData(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
                MarketData(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
            ),
            BaseMarketDataToDomainMapper()
        ) else MarketsDomain.Fail(ErrorType.GENERIC_ERROR)
    }

    class TestSearchMarketsUseCase(
        private val success: Boolean = true,
        private val isListEmpty: Boolean = false
    ) : SearchMarketsUseCase {
        override suspend fun execute(query: String) = if (success) {
            if (isListEmpty)
                MarketsDomain.Success(listOf(), BaseMarketDataToDomainMapper())
            else MarketsDomain.Success(
                listOf(
                    MarketData(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
                ),
                BaseMarketDataToDomainMapper()
            )
        } else MarketsDomain.Fail(ErrorType.GENERIC_ERROR)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}