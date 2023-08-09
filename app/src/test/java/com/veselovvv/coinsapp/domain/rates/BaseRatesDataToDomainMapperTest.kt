package com.veselovvv.coinsapp.domain.rates

import com.veselovvv.coinsapp.core.ErrorType
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseRatesDataToDomainMapperTest {
    private val rateMapper = BaseRateDataToDomainMapper()
    private val mapper = BaseRatesDataToDomainMapper(rateMapper)

    @Test
    fun test_success() {
        val rates = listOf(
            RateData(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
            RateData(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
        )

        val expected = RatesDomain.Success(rates, rateMapper)
        val actual = mapper.map(rates)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = RatesDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = RatesDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}