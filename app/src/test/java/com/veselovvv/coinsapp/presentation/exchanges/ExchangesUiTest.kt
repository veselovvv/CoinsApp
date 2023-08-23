package com.veselovvv.coinsapp.presentation.exchanges

import org.junit.Assert.assertEquals
import org.junit.Test

class ExchangesUiTest {
    @Test
    fun test_success() {
        val exchanges = listOf(
            ExchangeDomain(id = "okex", name = "Okex", rank = "1"),
            ExchangeDomain(id = "bithumb", name = "Bithumb", rank = "2")
        )

        var ui = ExchangesUi.Success(exchanges, BaseExchangeDomainToUiMapper())
        val communication = TestExchangesCommunication()
        ui.map(communication)

        var expected = listOf<ExchangeUi>(
            ExchangeUi.Base(id = "okex", name = "Okex", rank = "1"),
            ExchangeUi.Base(id = "bithumb", name = "Bithumb", rank = "2")
        )

        var actual = communication.getExchanges()
        assertEquals(expected, actual)

        ui = ExchangesUi.Success(listOf(), BaseExchangeDomainToUiMapper())
        ui.map(communication)

        expected = listOf<ExchangeUi>(ExchangeUi.NoResults)
        actual = communication.getExchanges()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val ui = ExchangesUi.Fail(GENERIC_ERROR_MESSAGE)
        val communication = TestExchangesCommunication()
        ui.map(communication)

        val expected = listOf<ExchangeUi>(ExchangeUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getExchanges()
        assertEquals(expected, actual)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}