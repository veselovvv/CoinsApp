package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.exchanges.ExchangeData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class SearchExchangesUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val foundExchanges = listOf(ExchangeData(id = "bithumb", name = "Bithumb", rank = "2"))
        val exchangeMapper = BaseExchangeDataToDomainMapper()

        val useCase = SearchExchangesUseCase.Base(
            TestExchangesRepository(),
            BaseExchangesDataToDomainMapper(exchangeMapper)
        )

        val expected = ExchangesDomain.Success(foundExchanges, exchangeMapper)
        val actual = useCase.execute(query = "Bithumb")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var exchangeMapper = BaseExchangeDataToDomainMapper()

        var useCase = SearchExchangesUseCase.Base(
            TestExchangesRepository(UnknownHostException()),
            BaseExchangesDataToDomainMapper(exchangeMapper)
        )

        var expected = ExchangesDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute(query = "")
        assertEquals(expected, actual)

        exchangeMapper = BaseExchangeDataToDomainMapper()

        useCase = SearchExchangesUseCase.Base(
            TestExchangesRepository(Exception()),
            BaseExchangesDataToDomainMapper(exchangeMapper)
        )

        expected = ExchangesDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute(query = "")
        assertEquals(expected, actual)
    }
}