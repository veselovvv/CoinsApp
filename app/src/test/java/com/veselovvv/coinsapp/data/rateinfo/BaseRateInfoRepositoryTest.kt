package com.veselovvv.coinsapp.data.rateinfo

import com.veselovvv.coinsapp.data.TestException
import com.veselovvv.coinsapp.data.rateinfo.cloud.RateInfoCloud
import com.veselovvv.coinsapp.data.rateinfo.cloud.RateInfoCloudDataSource
import com.veselovvv.coinsapp.data.rateinfo.cloud.RateInfoCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseRateInfoRepositoryTest {
    @Test
    fun test_fetch_rate_info_success() = runBlocking {
        val repository = RateInfoRepository.Base(
            TestRateInfoCloudDataSource(true),
            RateInfoCloudMapper.Base(ToRateInfoMapper.Base())
        )

        val expected = RatesInfoData.Success(
            RateInfoData(
                currencySymbol = "₿",
                type = "crypto"
            )
        )
        val actual = repository.fetchRateInfo(id = "bitcoin")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_rate_info_fail() = runBlocking {
        val repository = RateInfoRepository.Base(
            TestRateInfoCloudDataSource(false),
            RateInfoCloudMapper.Base(ToRateInfoMapper.Base())
        )
        val expected = RatesInfoData.Fail(TestException(""))
        val actual = repository.fetchRateInfo(id = "bitcoin")
        assertEquals(expected, actual)
    }

    class TestRateInfoCloudDataSource(private val success: Boolean) : RateInfoCloudDataSource {
        override suspend fun fetchRateInfo(id: String) = if (success) RateInfoCloud(
            currencySymbol = "₿",
            type = "crypto"
        ) else throw TestException("")
    }
}