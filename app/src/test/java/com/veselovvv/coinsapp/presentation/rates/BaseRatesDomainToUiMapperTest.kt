package com.veselovvv.coinsapp.presentation.rates

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseRatesDomainToUiMapperTest {
    private val rateMapper = BaseRateDomainToUiMapper()
    private val mapper = BaseRatesDomainToUiMapper(TestResourceProvider(), rateMapper)

    @Test
    fun test_success() {
        val rates = listOf(
            RateDomain(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
            RateDomain(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
        )

        val expected = RatesUi.Success(rates, rateMapper)
        val actual = mapper.map(rates)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = RatesUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = RatesUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}