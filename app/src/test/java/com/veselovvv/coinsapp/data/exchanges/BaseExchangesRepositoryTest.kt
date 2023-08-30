package com.veselovvv.coinsapp.data.exchanges

import com.veselovvv.coinsapp.data.TestException
import com.veselovvv.coinsapp.data.exchanges.cloud.ExchangeCloud
import com.veselovvv.coinsapp.data.exchanges.cloud.ExchangesCloudDataSource
import com.veselovvv.coinsapp.data.exchanges.cloud.ExchangesCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseExchangesRepositoryTest {
    @Test
    fun test_fetch_exchanges_success() = runBlocking {
        val repository = ExchangesRepository.Base(
            TestExchangesCloudDataSource(true),
            ExchangesCloudMapper.Base(ToExchangeMapper.Base())
        )

        val expected = ExchangesData.Success(
            listOf(
                ExchangeData(id = "okex", name = "Okex", rank = "1"),
                ExchangeData(id = "bithumb", name = "Bithumb", rank = "2")
            )
        )

        val actual = repository.fetchExchanges()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_exchanges_fail() = runBlocking {
        val repository = ExchangesRepository.Base(
            TestExchangesCloudDataSource(false),
            ExchangesCloudMapper.Base(ToExchangeMapper.Base())
        )

        val expected = ExchangesData.Fail(TestException(""))
        val actual = repository.fetchExchanges()
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_exchanges_success() = runBlocking {
        val repository = ExchangesRepository.Base(
            TestExchangesCloudDataSource(true),
            ExchangesCloudMapper.Base(ToExchangeMapper.Base())
        )

        val expected = ExchangesData.Success(
            listOf(
                ExchangeData(id = "bithumb", name = "Bithumb", rank = "2")
            )
        )

        val actual = repository.searchExchanges(query = "Bithumb")
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_exchanges_fail() = runBlocking {
        val repository = ExchangesRepository.Base(
            TestExchangesCloudDataSource(true),
            ExchangesCloudMapper.Base(ToExchangeMapper.Base())
        )

        val expected = ExchangesData.Success(listOf())
        val actual = repository.searchExchanges(query = "Az")
        assertEquals(expected, actual)
    }

    class TestExchangesCloudDataSource(private val success: Boolean) : ExchangesCloudDataSource {
        override suspend fun fetchExchanges() = if (success) listOf(
            ExchangeCloud(id = "okex", name = "Okex", rank = "1"),
            ExchangeCloud(id = "bithumb", name = "Bithumb", rank = "2")
        ) else throw TestException("")
    }
}