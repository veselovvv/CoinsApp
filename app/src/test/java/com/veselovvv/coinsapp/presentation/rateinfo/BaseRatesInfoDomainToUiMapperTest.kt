package com.veselovvv.coinsapp.presentation.rateinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.domain.rateinfo.RateInfoDomain
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseRatesInfoDomainToUiMapperTest {
    private val rateInfoMapper = BaseRateInfoDomainToUiMapper()
    private val mapper = BaseRatesInfoDomainToUiMapper(TestResourceProvider(), rateInfoMapper)

    @Test
    fun test_success() {
        val rateInfo = RateInfoDomain(
            currencySymbol = "₿",
            type = "crypto"
        )
        val expected = RatesInfoUi.Success(rateInfo, rateInfoMapper)
        val actual = mapper.map(rateInfo)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = RatesInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = RatesInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}