package com.veselovvv.coinsapp.domain.rates

import com.veselovvv.coinsapp.core.ErrorType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class SearchRatesUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val foundRates = listOf(
            RateData(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
        )

        val rateMapper = BaseRateDataToDomainMapper()

        val useCase = SearchRatesUseCase.Base(
            TestRatesRepository(),
            BaseRatesDataToDomainMapper(rateMapper)
        )

        val expected = RatesDomain.Success(foundRates, rateMapper)
        val actual = useCase.execute(query = "MWK")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var rateMapper = BaseRateDataToDomainMapper()

        var useCase = SearchRatesUseCase.Base(
            TestRatesRepository(UnknownHostException()),
            BaseRatesDataToDomainMapper(rateMapper)
        )

        var expected = RatesDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute(query = "MWK")
        assertEquals(expected, actual)

        rateMapper = BaseRateDataToDomainMapper()

        useCase = SearchRatesUseCase.Base(
            TestRatesRepository(Exception()),
            BaseRatesDataToDomainMapper(rateMapper)
        )

        expected = RatesDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute(query = "MWK")
        assertEquals(expected, actual)
    }
}