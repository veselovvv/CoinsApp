package com.veselovvv.coinsapp.presentation.exchangeinfo

import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangeInfoDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class ExchangesInfoUiTest {
    @Test
    fun test_success() {
        val exchangeInfo = ExchangeInfoDomain(
            percentTotalVolume = "2.946801735133553120000000000000000000",
            volumeUsd = "84969370.4499608426167365",
            tradingPairs = "52",
            exchangeUrl = "https://kraken.com"
        )

        val mapper = BaseExchangeInfoDomainToUiMapper()
        val ui = ExchangesInfoUi.Success(exchangeInfo, mapper)
        val communication = TestExchangesInfoCommunication()
        ui.map(communication)

        val expected = ExchangeInfoUi.Base(
            percentTotalVolume = "2.946801735133553120000000000000000000",
            volumeUsd = "84969370.4499608426167365",
            tradingPairs = "52",
            exchangeUrl = "https://kraken.com"
        )
        val actual = communication.getExchangeInfo()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var ui = ExchangesInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var communication = TestExchangesInfoCommunication()
        ui.map(communication)

        var expected = ExchangeInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = communication.getExchangeInfo()
        assertEquals(expected, actual)

        ui = ExchangesInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        communication = TestExchangesInfoCommunication()
        ui.map(communication)

        expected = ExchangeInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = communication.getExchangeInfo()
        assertEquals(expected, actual)

        ui = ExchangesInfoUi.Fail(GENERIC_ERROR_MESSAGE)
        communication = TestExchangesInfoCommunication()
        ui.map(communication)

        expected = ExchangeInfoUi.Fail(GENERIC_ERROR_MESSAGE)
        actual = communication.getExchangeInfo()
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}