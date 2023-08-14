package com.veselovvv.coinsapp.domain.rates

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.rates.RateData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchRatesUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val rates = listOf(
            RateData(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
            RateData(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
        )

        val rateMapper = BaseRateDataToDomainMapper()

        val useCase = FetchRatesUseCase.Base(
            TestRatesRepository(),
            BaseRatesDataToDomainMapper(rateMapper)
        )

        val expected = RatesDomain.Success(rates, rateMapper)
        val actual = useCase.execute()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var rateMapper = BaseRateDataToDomainMapper()

        var useCase = FetchRatesUseCase.Base(
            TestRatesRepository(UnknownHostException()),
            BaseRatesDataToDomainMapper(rateMapper)
        )

        var expected = RatesDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute()
        assertEquals(expected, actual)

        rateMapper = BaseRateDataToDomainMapper()

        useCase = FetchRatesUseCase.Base(
            TestRatesRepository(Exception()),
            BaseRatesDataToDomainMapper(rateMapper)
        )

        expected = RatesDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute()
        assertEquals(expected, actual)
    }
}