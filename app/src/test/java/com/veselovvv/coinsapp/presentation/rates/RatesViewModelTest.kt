package com.veselovvv.coinsapp.presentation.rates

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.rates.RateData
import com.veselovvv.coinsapp.domain.rates.BaseRateDataToDomainMapper
import com.veselovvv.coinsapp.domain.rates.FetchRatesUseCase
import com.veselovvv.coinsapp.domain.rates.RatesDomain
import com.veselovvv.coinsapp.domain.rates.SearchRatesUseCase
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class RatesViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_rates_success() = runBlocking {
        val communication = TestRatesCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = RatesViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchRatesUseCase(success = true),
            TestSearchRatesUseCase(),
            BaseRatesDomainToUiMapper(TestResourceProvider(), BaseRateDomainToUiMapper()),
            TestRateCache()
        )

        viewModel.fetchRates()

        val expected = listOf<RateUi>(
            RateUi.Base(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
            RateUi.Base(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
        )

        val actual = communication.getRates()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_rates_fail() = runBlocking {
        val communication = TestRatesCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = RatesViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchRatesUseCase(success = false),
            TestSearchRatesUseCase(),
            BaseRatesDomainToUiMapper(TestResourceProvider(), BaseRateDomainToUiMapper()),
            TestRateCache()
        )

        viewModel.fetchRates()

        val expected = listOf<RateUi>(RateUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getRates()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_search_rates_success() = runBlocking {
        val communication = TestRatesCommunication()
        val dispatchers = UnconfinedTestDispatcher()
        val fetchRatesUseCase = TestFetchRatesUseCase()
        val ratesMapper = BaseRatesDomainToUiMapper(TestResourceProvider(), BaseRateDomainToUiMapper())
        val rateCache = TestRateCache()

        var viewModel = RatesViewModel(
            communication,
            dispatchers,
            dispatchers,
            fetchRatesUseCase,
            TestSearchRatesUseCase(success = true, isListEmpty = false),
            ratesMapper,
            rateCache
        )

        viewModel.searchRates(query = "MWK")

        var expected = listOf<RateUi>(
            RateUi.Base(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
        )

        var actual = communication.getRates()
        assertEquals(expected, actual)

        viewModel = RatesViewModel(
            communication,
            dispatchers,
            dispatchers,
            fetchRatesUseCase,
            TestSearchRatesUseCase(success = true, isListEmpty = true),
            ratesMapper,
            rateCache
        )

        viewModel.searchRates(query = "MWKSC")

        expected = listOf<RateUi>(RateUi.NoResults)
        actual = communication.getRates()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_search_rates_fail() = runBlocking {
        val communication = TestRatesCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = RatesViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchRatesUseCase(success = false),
            TestSearchRatesUseCase(success = false, isListEmpty = false),
            BaseRatesDomainToUiMapper(TestResourceProvider(), BaseRateDomainToUiMapper()),
            TestRateCache()
        )

        viewModel.searchRates(query = "MWK")

        val expected = listOf<RateUi>(RateUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getRates()
        assertEquals(expected, actual)
    }

    class TestFetchRatesUseCase(private val success: Boolean = true) : FetchRatesUseCase {
        override suspend fun execute() = if (success) RatesDomain.Success(
            listOf(
                RateData(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
                RateData(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
            ),
            BaseRateDataToDomainMapper()
        ) else RatesDomain.Fail(ErrorType.GENERIC_ERROR)
    }

    class TestSearchRatesUseCase(
        private val success: Boolean = true,
        private val isListEmpty: Boolean = false
    ) : SearchRatesUseCase {
        override suspend fun execute(query: String) = if (success) {
            if (isListEmpty)
                RatesDomain.Success(listOf(), BaseRateDataToDomainMapper())
            else RatesDomain.Success(
                listOf(RateData(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")),
                BaseRateDataToDomainMapper()
            )
        } else RatesDomain.Fail(ErrorType.GENERIC_ERROR)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}