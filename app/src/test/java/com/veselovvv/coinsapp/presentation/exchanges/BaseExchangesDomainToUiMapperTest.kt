package com.veselovvv.coinsapp.presentation.exchanges

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.domain.exchanges.ExchangeDomain
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseExchangesDomainToUiMapperTest {
    private val exchangeMapper = BaseExchangeDomainToUiMapper()
    private val mapper = BaseExchangesDomainToUiMapper(TestResourceProvider(), exchangeMapper)

    @Test
    fun test_success() {
        val exchanges = listOf(
            ExchangeDomain(id = "okex", name = "Okex", rank = "1"),
            ExchangeDomain(id = "bithumb", name = "Bithumb", rank = "2")
        )

        val expected = ExchangesUi.Success(exchanges, exchangeMapper)
        val actual = mapper.map(exchanges)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = ExchangesUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = ExchangesUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}