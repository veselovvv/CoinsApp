package com.veselovvv.coinsapp.domain.rateinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.rateinfo.RateInfoData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseRatesInfoDataToDomainMapperTest {
    private val rateInfoMapper = BaseRateInfoDataToDomainMapper()
    private val mapper = BaseRatesInfoDataToDomainMapper(rateInfoMapper)

    @Test
    fun test_success() {
        val rateInfo = RateInfoData(
            currencySymbol = "₿",
            type = "crypto"
        )

        val expected = RatesInfoDomain.Success(rateInfo, rateInfoMapper)
        val actual = mapper.map(rateInfo)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = RatesInfoDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = RatesInfoDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}