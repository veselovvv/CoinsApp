package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.exchanges.ExchangeData
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import com.veselovvv.coinsapp.presentation.exchanges.BaseExchangeDomainToUiMapper
import com.veselovvv.coinsapp.presentation.exchanges.BaseExchangesDomainToUiMapper
import com.veselovvv.coinsapp.presentation.exchanges.ExchangesUi
import org.junit.Assert.assertEquals
import org.junit.Test

class ExchangesDomainTest {
    @Test
    fun test_success() {
        val exchanges = listOf(
            ExchangeData(id = "okex", name = "Okex", rank = "1"),
            ExchangeData(id = "bithumb", name = "Bithumb", rank = "2")
        )

        val resultExchanges = listOf(
            ExchangeDomain(id = "okex", name = "Okex", rank = "1"),
            ExchangeDomain(id = "bithumb", name = "Bithumb", rank = "2")
        )

        val exchangeMapper = BaseExchangeDomainToUiMapper()
        val domain = ExchangesDomain.Success(exchanges, BaseExchangeDataToDomainMapper())
        val expected = ExchangesUi.Success(resultExchanges, exchangeMapper)
        val actual = domain.map(BaseExchangesDomainToUiMapper(TestResourceProvider(), exchangeMapper))
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = ExchangesDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = ExchangesUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(
            BaseExchangesDomainToUiMapper(TestResourceProvider(), BaseExchangeDomainToUiMapper())
        )
        assertEquals(expected, actual)

        domain = ExchangesDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = ExchangesUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(
            BaseExchangesDomainToUiMapper(TestResourceProvider(), BaseExchangeDomainToUiMapper())
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}