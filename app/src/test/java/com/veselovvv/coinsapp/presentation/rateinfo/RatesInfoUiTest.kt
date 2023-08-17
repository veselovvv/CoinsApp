package com.veselovvv.coinsapp.presentation.rateinfo

import org.junit.Assert.assertEquals
import org.junit.Test

class RatesInfoUiTest {
    @Test
    fun test_success() {
        val rateInfo = RateInfoDomain(
            currencySymbol = "₿",
            type = "crypto"
        )

        val mapper = BaseRateInfoDomainToUiMapper()
        val ui = RatesInfoUi.Success(rateInfo, mapper)
        val communication = TestRatesInfoCommunication()
        ui.map(communication)

        val expected = RateInfoUi.Base(
            currencySymbol = "₿",
            type = "crypto"
        )
        val actual = communication.getRateInfo()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var ui = RatesInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var communication = TestRatesInfoCommunication()
        ui.map(communication)

        var expected = RateInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = communication.getRateInfo()
        assertEquals(expected, actual)

        ui = RatesInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        communication = TestRatesInfoCommunication()
        ui.map(communication)

        expected = RateInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = communication.getRateInfo()
        assertEquals(expected, actual)

        ui = RatesInfoUi.Fail(GENERIC_ERROR_MESSAGE)
        communication = TestRatesInfoCommunication()
        ui.map(communication)

        expected = RateInfoUi.Fail(GENERIC_ERROR_MESSAGE)
        actual = communication.getRateInfo()
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}