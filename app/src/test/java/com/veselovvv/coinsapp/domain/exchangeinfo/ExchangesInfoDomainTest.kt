package com.veselovvv.coinsapp.domain.exchangeinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoData
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import com.veselovvv.coinsapp.presentation.exchangeinfo.BaseExchangeInfoDomainToUiMapper
import com.veselovvv.coinsapp.presentation.exchangeinfo.BaseExchangesInfoDomainToUiMapper
import com.veselovvv.coinsapp.presentation.exchangeinfo.ExchangesInfoUi
import org.junit.Assert.assertEquals
import org.junit.Test

class ExchangesInfoDomainTest {
    @Test
    fun test_success() {
        val exchangeInfo = ExchangeInfoData(
            percentTotalVolume = "2.946801735133553120000000000000000000",
            volumeUsd = "84969370.4499608426167365",
            tradingPairs = "52",
            exchangeUrl = "https://kraken.com"
        )

        val domain = ExchangesInfoDomain.Success(exchangeInfo, BaseExchangeInfoDataToDomainMapper())

        val resultExchangeInfo = ExchangeInfoDomain(
            percentTotalVolume = "2.946801735133553120000000000000000000",
            volumeUsd = "84969370.4499608426167365",
            tradingPairs = "52",
            exchangeUrl = "https://kraken.com"
        )

        val exchangeInfoMapper = BaseExchangeInfoDomainToUiMapper()
        val expected = ExchangesInfoUi.Success(resultExchangeInfo, exchangeInfoMapper)
        val actual = domain.map(
            BaseExchangesInfoDomainToUiMapper(TestResourceProvider(), exchangeInfoMapper)
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = ExchangesInfoDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = ExchangesInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(
            BaseExchangesInfoDomainToUiMapper(TestResourceProvider(), BaseExchangeInfoDomainToUiMapper())
        )
        assertEquals(expected, actual)

        domain = ExchangesInfoDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = ExchangesInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(
            BaseExchangesInfoDomainToUiMapper(TestResourceProvider(), BaseExchangeInfoDomainToUiMapper())
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}