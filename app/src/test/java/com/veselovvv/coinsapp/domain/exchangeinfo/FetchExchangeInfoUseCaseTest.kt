package com.veselovvv.coinsapp.domain.exchangeinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchExchangeInfoUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val exchangeInfo = ExchangeInfoData(
            percentTotalVolume = "2.946801735133553120000000000000000000",
            volumeUsd = "84969370.4499608426167365",
            tradingPairs = "52",
            exchangeUrl = "https://kraken.com"
        )

        val exchangeInfoDataToDomainMapper = BaseExchangeInfoDataToDomainMapper()
        val useCase = FetchExchangeInfoUseCase.Base(
            TestExchangeInfoRepository(),
            BaseExchangesInfoDataToDomainMapper(exchangeInfoDataToDomainMapper)
        )
        val expected = ExchangesInfoDomain.Success(exchangeInfo, exchangeInfoDataToDomainMapper)
        val actual = useCase.execute(id = "kraken")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        val exchangeInfoDataToDomainMapper = BaseExchangeInfoDataToDomainMapper()
        var useCase = FetchExchangeInfoUseCase.Base(
            TestExchangeInfoRepository(UnknownHostException()),
            BaseExchangesInfoDataToDomainMapper(exchangeInfoDataToDomainMapper)
        )
        var expected = ExchangesInfoDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute(id = "kraken")
        assertEquals(expected, actual)

        useCase = FetchExchangeInfoUseCase.Base(
            TestExchangeInfoRepository(Exception()),
            BaseExchangesInfoDataToDomainMapper(exchangeInfoDataToDomainMapper)
        )
        expected = ExchangesInfoDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute(id = "kraken")
        assertEquals(expected, actual)
    }
}