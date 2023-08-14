package com.veselovvv.coinsapp.domain.rates

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.rates.RateData
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import com.veselovvv.coinsapp.presentation.rates.BaseRateDomainToUiMapper
import com.veselovvv.coinsapp.presentation.rates.BaseRatesDomainToUiMapper
import com.veselovvv.coinsapp.presentation.rates.RatesUi
import org.junit.Assert.assertEquals
import org.junit.Test

class RatesDomainTest {
    @Test
    fun test_success() {
        val rates = listOf(
            RateData(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
            RateData(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
        )

        val resultRates = listOf(
            RateDomain(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
            RateDomain(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
        )

        val rateMapper = BaseRateDomainToUiMapper()
        val domain = RatesDomain.Success(rates, BaseRateDataToDomainMapper())
        val expected = RatesUi.Success(resultRates, rateMapper)
        val actual = domain.map(BaseRatesDomainToUiMapper(TestResourceProvider(), rateMapper))
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = RatesDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = RatesUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(
            BaseRatesDomainToUiMapper(TestResourceProvider(), BaseRateDomainToUiMapper())
        )
        assertEquals(expected, actual)

        domain = RatesDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = RatesUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(
            BaseRatesDomainToUiMapper(TestResourceProvider(), BaseRateDomainToUiMapper())
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}