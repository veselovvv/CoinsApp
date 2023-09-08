package com.veselovvv.coinsapp.presentation.exchangeinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoData
import com.veselovvv.coinsapp.domain.exchangeinfo.BaseExchangeInfoDataToDomainMapper
import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangesInfoDomain
import com.veselovvv.coinsapp.domain.exchangeinfo.FetchExchangeInfoUseCase
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import com.veselovvv.coinsapp.presentation.exchanges.TestExchangeCache
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class ExchangesInfoViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_exchange_info_success() = runBlocking {
        val communication = TestExchangesInfoCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = ExchangesInfoViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchExchangeInfoUseCase(),
            BaseExchangesInfoDomainToUiMapper(TestResourceProvider(), BaseExchangeInfoDomainToUiMapper()),
            TestExchangeCache()
        )
        viewModel.fetchExchangeInfo(id = "kraken")

        val expected = ExchangeInfoUi.Base(
            percentTotalVolume = "2.946801735133553120000000000000000000",
            volumeUsd = "84969370.4499608426167365",
            tradingPairs = "52",
            exchangeUrl = "https://kraken.com"
        )
        val actual = communication.getExchangeInfo()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_exchange_info_fail() = runBlocking {
        var communication = TestExchangesInfoCommunication()
        var dispatchers = UnconfinedTestDispatcher()

        var viewModel = ExchangesInfoViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchExchangeInfoUseCase(ErrorType.NO_CONNECTION),
            BaseExchangesInfoDomainToUiMapper(TestResourceProvider(), BaseExchangeInfoDomainToUiMapper()),
            TestExchangeCache()
        )
        viewModel.fetchExchangeInfo(id = "kraken")

        var expected = ExchangeInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = communication.getExchangeInfo()
        assertEquals(expected, actual)

        communication = TestExchangesInfoCommunication()
        dispatchers = UnconfinedTestDispatcher()

        viewModel = ExchangesInfoViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchExchangeInfoUseCase(ErrorType.SERVICE_UNAVAILABLE),
            BaseExchangesInfoDomainToUiMapper(TestResourceProvider(), BaseExchangeInfoDomainToUiMapper()),
            TestExchangeCache()
        )
        viewModel.fetchExchangeInfo(id = "kraken")

        expected = ExchangeInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = communication.getExchangeInfo()
        assertEquals(expected, actual)

        communication = TestExchangesInfoCommunication()
        dispatchers = UnconfinedTestDispatcher()

        viewModel = ExchangesInfoViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchExchangeInfoUseCase(ErrorType.GENERIC_ERROR),
            BaseExchangesInfoDomainToUiMapper(TestResourceProvider(), BaseExchangeInfoDomainToUiMapper()),
            TestExchangeCache()
        )
        viewModel.fetchExchangeInfo(id = "kraken")

        expected = ExchangeInfoUi.Fail(GENERIC_ERROR_MESSAGE)
        actual = communication.getExchangeInfo()
        assertEquals(expected, actual)
    }

    class TestFetchExchangeInfoUseCase(
        private val error: ErrorType? = null
    ) : FetchExchangeInfoUseCase {
        override suspend fun execute(id: String) = if (error == null) ExchangesInfoDomain.Success(
            ExchangeInfoData(
                percentTotalVolume = "2.946801735133553120000000000000000000",
                volumeUsd = "84969370.4499608426167365",
                tradingPairs = "52",
                exchangeUrl = "https://kraken.com"
            ),
            BaseExchangeInfoDataToDomainMapper()
        ) else ExchangesInfoDomain.Fail(error)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}