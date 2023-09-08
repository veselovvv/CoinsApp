package com.veselovvv.coinsapp.domain.exchangeinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

interface BaseExchangesInfoDataToDomainMapperTest {
    private val exchangeInfoMapper = BaseExchangeInfoDataToDomainMapper()
    private val mapper = BaseExchangesInfoDataToDomainMapper(exchangeInfoMapper)

    @Test
    fun test_success() {
        val exchangeInfo = ExchangeInfoData(
            percentTotalVolume = "2.946801735133553120000000000000000000",
            volumeUsd = "84969370.4499608426167365",
            tradingPairs = "52",
            exchangeUrl = "https://kraken.com"
        )

        val expected = ExchangesInfoDomain.Success(exchangeInfo, exchangeInfoMapper)
        val actual = mapper.map(exchangeInfo)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = ExchangesInfoDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = ExchangesInfoDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}