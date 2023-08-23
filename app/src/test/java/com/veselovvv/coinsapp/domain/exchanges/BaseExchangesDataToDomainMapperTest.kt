package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.core.ErrorType
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseExchangesDataToDomainMapperTest {
    private val exchangeMapper = BaseExchangeDataToDomainMapper()
    private val mapper = BaseExchangesDataToDomainMapper(exchangeMapper)

    @Test
    fun test_success() {
        val exchanges = listOf(
            ExchangeData(id = "okex", name = "Okex", rank = "1"),
            ExchangeData(id = "bithumb", name = "Bithumb", rank = "2")
        )

        val expected = ExchangesDomain.Success(exchanges, exchangeMapper)
        val actual = mapper.map(exchanges)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = ExchangesDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = ExchangesDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}