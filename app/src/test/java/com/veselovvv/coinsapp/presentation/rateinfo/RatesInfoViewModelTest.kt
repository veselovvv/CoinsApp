package com.veselovvv.coinsapp.presentation.rateinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.rateinfo.RateInfoData
import com.veselovvv.coinsapp.domain.rateinfo.BaseRateInfoDataToDomainMapper
import com.veselovvv.coinsapp.domain.rateinfo.FetchRateInfoUseCase
import com.veselovvv.coinsapp.domain.rateinfo.RatesInfoDomain
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import com.veselovvv.coinsapp.presentation.rates.TestRateCache
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class RatesInfoViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_rate_info_success() = runBlocking {
        val communication = TestRatesInfoCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = RatesInfoViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchRateInfoUseCase(),
            BaseRatesInfoDomainToUiMapper(TestResourceProvider(), BaseRateInfoDomainToUiMapper()),
            TestRateCache()
        )
        viewModel.fetchRateInfo(id = "bitcoin")

        val expected = RateInfoUi.Base(
            currencySymbol = "₿",
            type = "crypto"
        )
        val actual = communication.getRateInfo()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_rate_info_fail() = runBlocking {
        var communication = TestRatesInfoCommunication()
        var dispatchers = UnconfinedTestDispatcher()

        var viewModel = RatesInfoViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchRateInfoUseCase(ErrorType.NO_CONNECTION),
            BaseRatesInfoDomainToUiMapper(TestResourceProvider(), BaseRateInfoDomainToUiMapper()),
            TestRateCache()
        )
        viewModel.fetchRateInfo(id = "bitcoin")

        var expected = RateInfoUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = communication.getRateInfo()
        assertEquals(expected, actual)

        communication = TestRatesInfoCommunication()
        dispatchers = UnconfinedTestDispatcher()

        viewModel = RatesInfoViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchRateInfoUseCase(ErrorType.SERVICE_UNAVAILABLE),
            BaseRatesInfoDomainToUiMapper(TestResourceProvider(), BaseRateInfoDomainToUiMapper()),
            TestRateCache()
        )
        viewModel.fetchRateInfo(id = "bitcoin")

        expected = RateInfoUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = communication.getRateInfo()
        assertEquals(expected, actual)

        communication = TestRatesInfoCommunication()
        dispatchers = UnconfinedTestDispatcher()

        viewModel = RatesInfoViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchRateInfoUseCase(ErrorType.GENERIC_ERROR),
            BaseRatesInfoDomainToUiMapper(TestResourceProvider(), BaseRateInfoDomainToUiMapper()),
            TestRateCache()
        )
        viewModel.fetchRateInfo(id = "bitcoin")

        expected = RateInfoUi.Fail(GENERIC_ERROR_MESSAGE)
        actual = communication.getRateInfo()
        assertEquals(expected, actual)
    }

    class TestFetchRateInfoUseCase(private val error: ErrorType? = null) : FetchRateInfoUseCase {
        override suspend fun execute(id: String) = if (error == null) RatesInfoDomain.Success(
            RateInfoData(
                currencySymbol = "₿",
                type = "crypto"
            ),
            BaseRateInfoDataToDomainMapper()
        ) else RatesInfoDomain.Fail(error)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}