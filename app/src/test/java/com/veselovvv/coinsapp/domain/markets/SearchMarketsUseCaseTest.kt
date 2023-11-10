package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.core.ErrorType
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.UnknownHostException

class SearchMarketsUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val foundMarkets = listOf(
            MarketData(exchangeId = "bitstamp", baseSymbol = "BTC", quoteSymbol = "USD")
        )

        val marketMapper = BaseMarketDataToDomainMapper()

        val useCase = SeachMarketsUseCase.Base(
            TestMarketsRepository(),
            BaseMarketsDataToDomainMapper(marketMapper)
        )

        val expected = MarketsDomain.Success(foundMarkets, marketMapper)
        val actual = useCase.execute(query = "bitstamp")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var marketMapper = BaseMarketDataToDomainMapper()

        var useCase = SeachMarketsUseCase.Base(
            TestMarketsRepository(UnknownHostException()),
            BaseMarketsDataToDomainMapper(marketMapper)
        )

        var expected = MarketsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute(query = "")
        assertEquals(expected, actual)

        marketMapper = BaseMarketDataToDomainMapper()

        useCase = SeachMarketsUseCase.Base(
            TestMarketsRepository(Exception()),
            BaseMarketsDataToDomainMapper(marketMapper)
        )

        expected = MarketsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute(query = "")
        assertEquals(expected, actual)
    }
}