package com.veselovvv.coinsapp.presentation.rates

import org.junit.Assert.assertEquals
import org.junit.Test

class RatesUiTest {
    @Test
    fun test_success() {
        val rates = listOf(
            RateDomain(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
            RateDomain(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
        )

        var ui = RatesUi.Success(rates, BaseRateDomainToUiMapper())
        val communication = TestRatesCommunication()
        ui.map(communication)

        var expected = listOf<RateUi>(
            RateUi.Base(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
            RateUi.Base(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
        )

        var actual = communication.getRates()
        assertEquals(expected, actual)

        ui = RatesUi.Success(listOf(), BaseRateDomainToUiMapper())
        ui.map(communication)

        expected = listOf<RateUi>(RateUi.NoResults)
        actual = communication.getRates()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val ui = RatesUi.Fail(GENERIC_ERROR_MESSAGE)
        val communication = TestRatesCommunication()
        ui.map(communication)

        val expected = listOf<RateUi>(RateUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getRates()
        assertEquals(expected, actual)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}