package com.veselovvv.coinsapp.data.exchanges

interface ToExchangeMapper {
    fun map(id: String, name: String, rank: String): ExchangeData

    class Base : ToExchangeMapper {
        override fun map(id: String, name: String, rank: String) = ExchangeData(id, name, rank)
    }
}