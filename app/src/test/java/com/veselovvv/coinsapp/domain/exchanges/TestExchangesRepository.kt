package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.data.exchanges.ExchangeData
import com.veselovvv.coinsapp.data.exchanges.ExchangesData
import com.veselovvv.coinsapp.data.exchanges.ExchangesRepository

class TestExchangesRepository(private val exception: Exception? = null) : ExchangesRepository {
    private val exchanges = listOf(
        ExchangeData(id = "okex", name = "Okex", rank = "1"),
        ExchangeData(id = "bithumb", name = "Bithumb", rank = "2")
    )

    private val foundExchanges = listOf(ExchangeData(id = "bithumb", name = "Bithumb", rank = "2"))

    override suspend fun fetchExchanges() =
        if (exception == null) ExchangesData.Success(exchanges) else ExchangesData.Fail(exception)

    override suspend fun searchExchanges(query: String) =
        if (exception == null) ExchangesData.Success(foundExchanges) else ExchangesData.Fail(exception)
}