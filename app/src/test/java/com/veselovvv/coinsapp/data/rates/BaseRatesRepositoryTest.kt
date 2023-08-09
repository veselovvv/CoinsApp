package com.veselovvv.coinsapp.data.rates

import com.veselovvv.coinsapp.data.TestException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseRatesRepositoryTest {
    @Test
    fun test_fetch_rates_success() = runBlocking {
        val repository = RatesRepository.Base(
            TestRatesCloudDataSource(true),
            RatesCloudMapper.Base(ToRateMapper.Base())
        )

        val expected = RatesData.Success(
            listOf(
                RateData(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
                RateData(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
            )
        )

        val actual = repository.fetchRates()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_rates_fail() = runBlocking {
        val repository = RatesRepository.Base(
            TestRatesCloudDataSource(false),
            RatesCloudMapper.Base(ToRateMapper.Base())
        )

        val expected = RatesData.Fail(TestException(""))
        val actual = repository.fetchRates()
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_rates_success() = runBlocking {
        val repository = RatesRepository.Base(
            TestRatesCloudDataSource(true),
            RatesCloudMapper.Base(ToRateMapper.Base())
        )

        val expected = RatesData.Success(
            listOf(
                RateData(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
            )
        )

        val actual = repository.searchRates(query = "MWK")
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_rates_fail() = runBlocking {
        val repository = RatesRepository.Base(
            TestRatesCloudDataSource(true),
            RatesCloudMapper.Base(ToRateMapper.Base())
        )

        val expected = RatesData.Success(listOf())
        val actual = repository.searchRates(query = "MWKFG")
        assertEquals(expected, actual)
    }

    class TestRatesCloudDataSource(private val success: Boolean) : RatesCloudDataSource {
        override suspend fun fetchRates() = if (success) listOf(
            RateCloud(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
            RateCloud(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
        ) else throw TestException("")
    }
}