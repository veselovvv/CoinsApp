package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.core.ErrorType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchMarketsUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val markets = listOf(
            MarketData(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD"),
            MarketData(exchangeId = "zb", baseSymbol = "DASH", quoteSymbol = "USDT")
        )

        val marketMapper = BaseMarketDataToDomainMapper()

        val useCase = FetchMarketsUseCase.Base(
            TestMarketsRepository(),
            BaseMarketsDataToDomainMapper(marketMapper)
        )

        val expected = MarketsDomain.Success(markets, marketMapper)
        val actual = useCase.execute()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var marketMapper = BaseMarketDataToDomainMapper()

        var useCase = FetchMarketsUseCase.Base(
            TestMarketsRepository(UnknownHostException()),
            BaseMarketsDataToDomainMapper(marketMapper)
        )

        var expected = MarketsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute()
        assertEquals(expected, actual)

        marketMapper = BaseMarketDataToDomainMapper()

        useCase = FetchMarketsUseCase.Base(
            TestMarketsRepository(Exception()),
            BaseMarketsDataToDomainMapper(marketMapper)
        )

        expected = MarketsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute()
        assertEquals(expected, actual)
    }
}