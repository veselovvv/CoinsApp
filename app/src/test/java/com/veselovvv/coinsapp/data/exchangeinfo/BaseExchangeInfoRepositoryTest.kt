package com.veselovvv.coinsapp.data.exchangeinfo

import com.veselovvv.coinsapp.data.TestException
import com.veselovvv.coinsapp.data.exchangeinfo.cloud.ExchangeInfoCloud
import com.veselovvv.coinsapp.data.exchangeinfo.cloud.ExchangeInfoCloudDataSource
import com.veselovvv.coinsapp.data.exchangeinfo.cloud.ExchangeInfoCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseExchangeInfoRepositoryTest {
    @Test
    fun test_fetch_exchange_info_success() = runBlocking {
        val repository = ExchangeInfoRepository.Base(
            TestExchangeInfoCloudDataSource(true),
            ExchangeInfoCloudMapper.Base(ToExchangeInfoMapper.Base())
        )

        val expected = ExchangesInfoData.Success(
            ExchangeInfoData(
                percentTotalVolume = "2.946801735133553120000000000000000000",
                volumeUsd = "84969370.4499608426167365",
                tradingPairs = "52",
                exchangeUrl = "https://kraken.com"
            )
        )
        val actual = repository.fetchExchangeInfo(id = "kraken")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_exchange_info_fail() = runBlocking {
        val repository = ExchangeInfoRepository.Base(
            TestExchangeInfoCloudDataSource(false),
            ExchangeInfoCloudMapper.Base(ToExchangeInfoMapper.Base())
        )
        val expected = ExchangesInfoData.Fail(TestException(""))
        val actual = repository.fetchExchangeInfo(id = "kraken")
        assertEquals(expected, actual)
    }

    class TestExchangeInfoCloudDataSource(private val success: Boolean) : ExchangeInfoCloudDataSource {
        override suspend fun fetchExchangeInfo(id: String) = if (success) ExchangeInfoCloud(
            percentTotalVolume = "2.946801735133553120000000000000000000",
            volumeUsd = "84969370.4499608426167365",
            tradingPairs = "52",
            exchangeUrl = "https://kraken.com"
        ) else throw TestException("")
    }
}