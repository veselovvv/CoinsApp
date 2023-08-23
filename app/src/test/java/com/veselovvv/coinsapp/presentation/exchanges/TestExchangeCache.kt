package com.veselovvv.coinsapp.presentation.exchanges

class TestExchangeCache : ExchangeCache {
    override fun save(data: Triple<String, String, String>) = Unit
    override fun read() = Triple("okex", "Okex", "1")
}