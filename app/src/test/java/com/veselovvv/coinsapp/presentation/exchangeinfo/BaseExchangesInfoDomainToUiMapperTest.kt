package com.veselovvv.coinsapp.presentation.exchangeinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseExchangesInfoDomainToUiMapperTest {
    private val exchangeInfoMapper = BaseExchangeInfoDomainToUiMapper()
    private val mapper = BaseExchangesInfoDomainToUiMapper(TestResourceProvider(), exchangeInfoMapper)

    @Test
    fun test_success() {
        val exchangeInfo = ExchangeInfoDomain(
            percentTotalVolume = "2.946801735133553120000000000000000000",
            volumeUsd = "84969370.4499608426167365",
            tradingPairs = "52",
            exchangeUrl = "https://kraken.com"
        )
        val expected = ExchangesInfoUi.Success(exchangeInfo, exchangeInfoMapper)
        val actual = mapper.map(exchangeInfo)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = ExchangesInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = ExchangesInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}