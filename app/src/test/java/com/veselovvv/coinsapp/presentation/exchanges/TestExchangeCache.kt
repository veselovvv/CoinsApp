package com.veselovvv.coinsapp.presentation.exchanges

class TestExchangeCache : ExchangeCache {
    override fun save(data: ExchangesParameters) = Unit
    override fun read() = ExchangesParameters("okex", "Okex", "1")
}