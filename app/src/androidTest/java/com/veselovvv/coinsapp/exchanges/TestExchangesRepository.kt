package com.veselovvv.coinsapp.exchanges

import com.veselovvv.coinsapp.data.exchanges.ExchangeData
import com.veselovvv.coinsapp.data.exchanges.ExchangesData
import com.veselovvv.coinsapp.data.exchanges.ExchangesRepository
import java.net.UnknownHostException

class TestExchangesRepository : ExchangesRepository {
    private var isSuccess = false

    private val exchanges = listOf(
        ExchangeData(id = "binance", name = "Binance", rank = "1"),
        ExchangeData(id = "crypto", name = "Crypto.com Exchange", rank = "2"),
        ExchangeData(id = "gdax", name = "Coinbase Pro", rank = "3")
    )

    private val foundExchanges = listOf(ExchangeData(id = "gdax", name = "Coinbase Pro", rank = "3"))

    override suspend fun fetchExchanges(): ExchangesData {
        isSuccess = !isSuccess

        return if (isSuccess)
            ExchangesData.Success(exchanges)
        else
            ExchangesData.Fail(UnknownHostException())
    }

    override suspend fun searchExchanges(query: String) =
        ExchangesData.Success(
            when {
                query == "" -> exchanges
                query.startsWith("Co") -> foundExchanges
                else -> listOf()
            }
        )
}