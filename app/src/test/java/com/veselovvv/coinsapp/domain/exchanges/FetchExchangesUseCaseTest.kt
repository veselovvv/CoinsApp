package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.core.ErrorType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchExchangesUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val exchanges = listOf(
            ExchangeData(id = "okex", name = "Okex", rank = "1"),
            ExchangeData(id = "bithumb", name = "Bithumb", rank = "2")
        )

        val exchangeMapper = BaseExchangeDataToDomainMapper()

        val useCase = FetchExchangesUseCase.Base(
            TestExchangesRepository(),
            BaseExchangesDataToDomainMapper(exchangeMapper)
        )

        val expected = ExchangesDomain.Success(exchanges, exchangeMapper)
        val actual = useCase.execute()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var exchangeMapper = BaseExchangeDataToDomainMapper()

        var useCase = FetchExchangesUseCase.Base(
            TestExchangesRepository(UnknownHostException()),
            BaseExchangesDataToDomainMapper(exchangeMapper)
        )

        var expected = ExchangesDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute()
        assertEquals(expected, actual)

        exchangeMapper = BaseExchangeDataToDomainMapper()

        useCase = FetchExchangesUseCase.Base(
            TestExchangesRepository(Exception()),
            BaseExchangesDataToDomainMapper(exchangeMapper)
        )

        expected = ExchangesDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute()
        assertEquals(expected, actual)
    }
}