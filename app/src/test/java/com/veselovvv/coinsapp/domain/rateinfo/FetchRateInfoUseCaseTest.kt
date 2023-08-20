package com.veselovvv.coinsapp.domain.rateinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.rateinfo.RateInfoData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchRateInfoUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val rateInfo = RateInfoData(
            currencySymbol = "₿",
            type = "crypto"
        )

        val rateInfoDataToDomainMapper = BaseRateInfoDataToDomainMapper()
        val useCase = FetchRateInfoUseCase.Base(
            TestRateInfoRepository(),
            BaseRatesInfoDataToDomainMapper(rateInfoDataToDomainMapper)
        )
        val expected = RatesInfoDomain.Success(rateInfo, rateInfoDataToDomainMapper)
        val actual = useCase.execute(id = "bitcoin")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        val rateInfoDataToDomainMapper = BaseRateInfoDataToDomainMapper()
        var useCase = FetchRateInfoUseCase.Base(
            TestRateInfoRepository(UnknownHostException()),
            BaseRatesInfoDataToDomainMapper(rateInfoDataToDomainMapper)
        )
        var expected = RatesInfoDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute(id = "bitcoin")
        assertEquals(expected, actual)

        useCase = FetchRateInfoUseCase.Base(
            TestRateInfoRepository(Exception()),
            BaseRatesInfoDataToDomainMapper(rateInfoDataToDomainMapper)
        )
        expected = RatesInfoDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute(id = "bitcoin")
        assertEquals(expected, actual)
    }
}