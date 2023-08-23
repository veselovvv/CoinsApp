package com.veselovvv.coinsapp.presentation.exchanges

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class ExchangesViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_exchanges_success() = runBlocking {
        val communication = TestExchangesCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = ExchangesViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchExchangesUseCase(success = true),
            TestSearchExchangesUseCase(),
            BaseExchangesDomainToUiMapper(TestResourceProvider(), BaseExchangeDomainToUiMapper()),
            TestExchangeCache()
        )

        viewModel.fetchExchanges()

        val expected = listOf<ExchangeUi>(
            ExchangeUi.Base(id = "okex", name = "Okex", rank = "1"),
            ExchangeUi.Base(id = "bithumb", name = "Bithumb", rank = "2")
        )

        val actual = communication.getExchanges()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_fetch_exchanges_fail() = runBlocking {
        val communication = TestExchangesCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = ExchangesViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchExchangesUseCase(success = false),
            TestSearchExchangesUseCase(),
            BaseExchangesDomainToUiMapper(TestResourceProvider(), BaseExchangeDomainToUiMapper()),
            TestExchangeCache()
        )

        viewModel.fetchExchanges()

        val expected = listOf<ExchangeUi>(ExchangeUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getExchanges()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_search_exchanges_success() = runBlocking {
        val communication = TestExchangesCommunication()
        val dispatchers = UnconfinedTestDispatcher()
        val fetchExchangesUseCase = TestFetchExchangesUseCase()
        val exchangesMapper = BaseExchangesDomainToUiMapper(
            TestResourceProvider(),
            BaseExchangeDomainToUiMapper()
        )
        val exchangeCache = TestExchangeCache()

        val viewModel = ExchangesViewModel(
            communication,
            dispatchers,
            dispatchers,
            fetchExchangesUseCase,
            TestSearchExchangesUseCase(success = true, isListEmpty = false),
            exchangesMapper,
            exchangeCache
        )

        viewModel.searchExchanges(query = "Bithumb")

        var expected = listOf<ExchangeUi>(
            ExchangeUi.Base(id = "bithumb", name = "Bithumb", rank = "2")
        )

        var actual = communication.getExchanges()
        assertEquals(expected, actual)

        viewModel = ExchangesViewModel(
            communication,
            dispatchers,
            dispatchers,
            fetchExchangesUseCase,
            TestSearchExchangesUseCase(success = true, isListEmpty = true),
            exchangesMapper,
            exchangeCache
        )

        viewModel.searchExchanges(query = "Element that does not exist")
        expected = listOf<ExchangeUi>(ExchangeUi.NoResults)
        actual = communication.getExchanges()
        assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_search_exchanges_fail() = runBlocking {
        val communication = TestExchangesCommunication()
        val dispatchers = UnconfinedTestDispatcher()

        val viewModel = ExchangesViewModel(
            communication,
            dispatchers,
            dispatchers,
            TestFetchExchangesUseCase(success = false),
            TestSearchExchangesUseCase(success = false, isListEmpty = false),
            BaseExchangesDomainToUiMapper(TestResourceProvider(), BaseExchangeDomainToUiMapper()),
            TestExchangeCache()
        )

        viewModel.searchExchanges(query = "Some query")

        val expected = listOf<ExchangeUi>(ExchangeUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getExchanges()
        assertEquals(expected, actual)
    }

    class TestFetchExchangesUseCase(private val success: Boolean = true) : FetchExchangesUseCase {
        override suspend fun execute() = if (success) ExchangesDomain.Success(
            listOf(
                ExchangeData(id = "okex", name = "Okex", rank = "1"),
                ExchangeData(id = "bithumb", name = "Bithumb", rank = "2")
            ),
            BaseExchangeDataToDomainMapper()
        ) else ExchangesDomain.Fail(ErrorType.GENERIC_ERROR)
    }

    class TestSearchExchangesUseCase(
        private val success: Boolean = true,
        private val isListEmpty: Boolean = false
    ) : SearchExchangesUseCase {
        override suspend fun execute(query: String) = if (success) {
            if (isListEmpty)
                ExchangesDomain.Success(listOf(), BaseExchangeDataToDomainMapper())
            else ExchangesDomain.Success(
                listOf(ExchangeData(id = "bithumb", name = "Bithumb", rank = "2")),
                BaseExchangeDataToDomainMapper()
            )
        } else ExchangesDomain.Fail(ErrorType.GENERIC_ERROR)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}