package com.veselovvv.coinsapp.data.exchanges.cloud

import com.veselovvv.coinsapp.data.exchanges.ExchangeData
import com.veselovvv.coinsapp.data.exchanges.ToExchangeMapper

interface ExchangesCloudMapper {
    fun map(exchanges: List<ExchangeCloud>): List<ExchangeData>

    class Base(private val exchangeMapper: ToExchangeMapper) : ExchangesCloudMapper {
        override fun map(exchanges: List<ExchangeCloud>) = exchanges.map { exchange ->
            exchange.map(exchangeMapper)
        }
    }
}