package com.veselovvv.coinsapp.domain.rateinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class RatesInfoDomainTest {
    @Test
    fun test_success() {
        val rateInfo = RateInfoData(
            currencySymbol = "₿",
            type = "crypto"
        )

        val domain = RatesInfoDomain.Success(rateInfo, BaseRateInfoDataToDomainMapper())

        val resultRateInfo = RateInfoDomain(
            currencySymbol = "₿",
            type = "crypto"
        )

        val rateInfoMapper = BaseRateInfoDomainToUiMapper()
        val expected = RatesInfoUi.Success(resultRateInfo, rateInfoMapper)
        val actual = domain.map(
            BaseRatesInfoDomainToUiMapper(TestResourceProvider(), rateInfoMapper)
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = RatesInfoDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = RatesInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(
            BaseRatesInfoDomainToUiMapper(TestResourceProvider(), BaseRateInfoDomainToUiMapper())
        )
        assertEquals(expected, actual)

        domain = RatesInfoDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = RatesInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(
            BaseRatesInfoDomainToUiMapper(TestResourceProvider(), BaseRateInfoDomainToUiMapper())
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}